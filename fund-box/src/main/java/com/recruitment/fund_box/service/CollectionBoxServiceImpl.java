package com.recruitment.fund_box.service;

import com.recruitment.fund_box.domain.CollectionBox;
import com.recruitment.fund_box.domain.FundraisingEvent;
import com.recruitment.fund_box.dto.response.CollectionBoxResponse;
import com.recruitment.fund_box.enums.Currency;
import com.recruitment.fund_box.exceptions.ResourceNotFoundException;
import com.recruitment.fund_box.mappers.CollectionBoxMapper;
import com.recruitment.fund_box.repository.CollectionBoxRepository;
import com.recruitment.fund_box.repository.FundraisingEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class CollectionBoxServiceImpl implements CollectionBoxService {
    private final CollectionBoxRepository collectionBoxRepository;
    private final FundraisingEventRepository fundraisingEventRepository;
    private final CollectionBoxMapper collectionBoxMapper;
    private final CurrencyExchangeService currencyExchangeService;

    public CollectionBoxServiceImpl(
            CollectionBoxRepository collectionBoxRepository,
            CollectionBoxMapper collectionBoxMapper,
            FundraisingEventRepository fundraisingEventRepository,
            CurrencyExchangeService currencyExchangeService) {
        this.collectionBoxRepository = collectionBoxRepository;
        this.collectionBoxMapper = collectionBoxMapper;
        this.fundraisingEventRepository = fundraisingEventRepository;
        this.currencyExchangeService = currencyExchangeService;
    }

    @Override
    public List<CollectionBoxResponse> getAllCollectionBoxes() {
        return collectionBoxMapper.collectionBoxesToCollectionBoxResponses(
            collectionBoxRepository.findAll()
        );
    }

    @Override
    public CollectionBoxResponse createCollectionBox() {
        CollectionBox box = new CollectionBox();
        CollectionBox saved = collectionBoxRepository.save(box);
        return collectionBoxMapper.collectionBoxToCollectionBoxDto(saved);
    }

    @Override
    public void assignCollectionBox(Long boxId, Long eventId){
        CollectionBox box = collectionBoxRepository.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("Collection Box not found with ID: " + boxId));

        if (box.getFundraisingEvent() != null) {
            throw new IllegalStateException("Collection Box is already assigned to an event");
        }

        if (!box.isEmpty()) {
            throw new IllegalStateException("Cannot assign non-empty collection box to event");
        }

        FundraisingEvent event = fundraisingEventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID " + eventId));

        box.setFundraisingEvent(event);
        collectionBoxRepository.save(box);
    }

    @Override
    public void removeCollectionBox(Long boxId) {
        if (!collectionBoxRepository.existsById(boxId)) {
            throw new ResourceNotFoundException("Collection Box not found with ID: " + boxId);
        }
        collectionBoxRepository.deleteById(boxId);
    }

    @Override
    public void makeDonation(Long id, Currency currency, BigDecimal amount) {
        CollectionBox box = collectionBoxRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collection Box not found with ID: " + id));

        if (box.getFundraisingEvent() == null) {
            throw new IllegalStateException("Cannot make donation to unassigned collection box");
        }

        box.addMoney(currency, amount);
        collectionBoxRepository.save(box);
    }

    @Override
    @Transactional
    public void transferFunds(Long boxId) {
        CollectionBox box = collectionBoxRepository.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("Collection Box not found with ID: " + boxId));

        if (box.getFundraisingEvent() == null) {
            throw new IllegalStateException("Cannot transfer funds from unassigned collection box");
        }

        if (box.isEmpty()) {
            throw new IllegalStateException("Cannot transfer funds from empty collection box");
        }

        FundraisingEvent event = box.getFundraisingEvent();
        Currency targetCurrency = event.getAccountCurrency();
        BigDecimal totalConvertedAmount = BigDecimal.ZERO;

        for (Map.Entry<Currency, BigDecimal> entry : box.getBalance().entrySet()) {
            Currency sourceCurrency = entry.getKey();
            BigDecimal amount = entry.getValue();
            
            BigDecimal convertedAmount = currencyExchangeService.convertCurrency(
                amount, sourceCurrency, targetCurrency);
            totalConvertedAmount = totalConvertedAmount.add(convertedAmount);
        }

        event.setFunds(event.getFunds().add(totalConvertedAmount));
        fundraisingEventRepository.save(event);

        box.setBalance(new HashMap<>());
        collectionBoxRepository.save(box);
    }
}

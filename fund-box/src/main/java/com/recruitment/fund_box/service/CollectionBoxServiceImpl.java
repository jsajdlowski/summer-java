package com.recruitment.fund_box.service;

import com.recruitment.fund_box.domain.CollectionBox;
import com.recruitment.fund_box.domain.FundraisingEvent;
import com.recruitment.fund_box.dto.request.CollectionBoxRequest;
import com.recruitment.fund_box.dto.response.CollectionBoxResponse;
import com.recruitment.fund_box.exceptions.ResourceNotFoundException;
import com.recruitment.fund_box.mappers.CollectionBoxMapper;
import com.recruitment.fund_box.repository.CollectionBoxRepository;
import com.recruitment.fund_box.repository.FundraisingEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionBoxServiceImpl implements CollectionBoxService {
    private CollectionBoxRepository collectionBoxRepository;
    private FundraisingEventRepository fundraisingEventRepository;
    private CollectionBoxMapper collectionBoxMapper;

    public CollectionBoxServiceImpl(
            CollectionBoxRepository collectionBoxRepository,
            CollectionBoxMapper collectionBoxMapper,
            FundraisingEventRepository fundraisingEventRepository) {
        this.collectionBoxRepository = collectionBoxRepository;
        this.collectionBoxMapper = collectionBoxMapper;
        this.fundraisingEventRepository = fundraisingEventRepository;
    }

    @Override
    public List<CollectionBoxResponse> getAllCollectionBoxes(){
        return collectionBoxRepository.findAll().stream().map(collectionBoxMapper::collectionBoxToCollectionBoxDto)
                .collect(Collectors.toList());
    }

    @Override
    public CollectionBoxResponse createCollectionBox(CollectionBoxRequest request) {
        CollectionBox box = new CollectionBox();
        CollectionBox saved = collectionBoxRepository.save(box);
        return collectionBoxMapper.collectionBoxToCollectionBoxDto(saved);
    }

    @Override
    public void assignCollectionBox(Long boxId, Long eventId){
        CollectionBox box = collectionBoxRepository.findById(boxId)
                .orElseThrow(() -> new ResourceNotFoundException("Collection Box not found with ID: " + boxId));

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


}

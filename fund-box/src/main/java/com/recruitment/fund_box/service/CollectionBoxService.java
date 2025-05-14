package com.recruitment.fund_box.service;

import com.recruitment.fund_box.dto.request.CollectionBoxRequest;
import com.recruitment.fund_box.dto.response.CollectionBoxResponse;
import com.recruitment.fund_box.enums.Currency;

import java.math.BigDecimal;
import java.util.List;

public interface CollectionBoxService {
    CollectionBoxResponse createCollectionBox(CollectionBoxRequest collectionBoxRequest);
    List<CollectionBoxResponse> getAllCollectionBoxes();
    void removeCollectionBox(Long boxId);
    void assignCollectionBox(Long boxId, Long eventId);
    void makeDonation(Long id, Currency currency, BigDecimal amount);
    void transferFunds(Long boxId);
}

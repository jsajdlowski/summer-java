package com.recruitment.fund_box.service;

import com.recruitment.fund_box.dto.request.CollectionBoxRequest;
import com.recruitment.fund_box.dto.response.CollectionBoxResponse;

import java.util.List;

public interface CollectionBoxService {
    CollectionBoxResponse createCollectionBox(CollectionBoxRequest collectionBoxRequest);
    List<CollectionBoxResponse> getAllCollectionBoxes();
    void removeCollectionBox(Long boxId);
    void assignCollectionBox(Long boxId, Long eventId);

}

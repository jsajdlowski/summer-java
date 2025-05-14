package com.recruitment.fund_box.controller;

import com.recruitment.fund_box.dto.request.DonationRequest;
import com.recruitment.fund_box.dto.response.CollectionBoxResponse;
import com.recruitment.fund_box.service.CollectionBoxService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/box")
public class CollectionBoxController {
    private CollectionBoxService collectionBoxService;

    public CollectionBoxController(CollectionBoxService collectionBoxService) {
        this.collectionBoxService = collectionBoxService;
    }

    @GetMapping()
    public ResponseEntity<List<CollectionBoxResponse>> getAllCollectionBoxes() {
        List<CollectionBoxResponse> response = collectionBoxService.getAllCollectionBoxes();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CollectionBoxResponse> createCollectionBox() {
        CollectionBoxResponse response = collectionBoxService.createCollectionBox();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{boxId}/donate")
    public ResponseEntity<Void> makeDonation(
            @PathVariable Long boxId,
            @Valid @RequestBody DonationRequest request) {
        collectionBoxService.makeDonation(boxId, request.currency(), request.amount());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{boxId}/transfer")
    public ResponseEntity<Void> transferFunds(@PathVariable Long boxId) {
        collectionBoxService.transferFunds(boxId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{boxId}")
    public ResponseEntity<Void> deleteCollectionBox(@PathVariable Long boxId) {
        collectionBoxService.removeCollectionBox(boxId);
        return ResponseEntity.noContent().build();
    }
}

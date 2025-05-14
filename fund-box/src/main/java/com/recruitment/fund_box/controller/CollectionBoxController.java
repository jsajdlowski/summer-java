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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollectionBox(@PathVariable Long id) {
        collectionBoxService.removeCollectionBox(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{boxId}/assign/{eventId}")
    public ResponseEntity<Void> assignToEvent(@PathVariable Long boxId, @PathVariable Long eventId) {
        collectionBoxService.assignCollectionBox(boxId, eventId);
        return ResponseEntity.ok().build(); 
    }

    @PostMapping("/{id}/donate")
    public ResponseEntity<Void> makeDonation(
            @PathVariable Long id,
            @Valid @RequestBody DonationRequest request) {
        collectionBoxService.makeDonation(id, request.currency(), request.amount());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<Void> transferFunds(@PathVariable Long id) {
        collectionBoxService.transferFunds(id);
        return ResponseEntity.ok().build();
    }
}

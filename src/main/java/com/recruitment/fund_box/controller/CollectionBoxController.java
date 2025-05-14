package com.recruitment.fund_box.controller;

import com.recruitment.fund_box.dto.request.DonationRequest;
import com.recruitment.fund_box.dto.response.CollectionBoxResponse;
import com.recruitment.fund_box.service.CollectionBoxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/box")
@Tag(name = "Collection Box", description = "Collection Box management APIs")
public class CollectionBoxController {
    private CollectionBoxService collectionBoxService;

    public CollectionBoxController(CollectionBoxService collectionBoxService) {
        this.collectionBoxService = collectionBoxService;
    }

    @GetMapping()
    @Operation(summary = "Get all collection boxes", description = "Retrieves a list of all collection boxes in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of collection boxes"),
    })
    public ResponseEntity<List<CollectionBoxResponse>> getAllCollectionBoxes() {
        List<CollectionBoxResponse> response = collectionBoxService.getAllCollectionBoxes();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Create a new collection box", description = "Creates a new empty collection box")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Collection box successfully created"),
    })
    public ResponseEntity<CollectionBoxResponse> createCollectionBox() {
        CollectionBoxResponse response = collectionBoxService.createCollectionBox();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{boxId}/donate")
    @Operation(summary = "Make a donation", description = "Add funds to a collection box")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "202",
            description = "Donation successfully accepted"
        )
    })
    public ResponseEntity<Void> makeDonation(
            @PathVariable Long boxId,
            @Valid @RequestBody DonationRequest request) {
        collectionBoxService.makeDonation(boxId, request.currency(), request.amount());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{boxId}/transfer")
    @Operation(summary = "Transfer funds", description = "Transfer all funds from a collection box to its associated event")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Funds successfully transferred")
    })
    public ResponseEntity<Void> transferFunds(@PathVariable Long boxId) {
        collectionBoxService.transferFunds(boxId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{boxId}")
    @Operation(summary = "Delete a collection box", description = "Removes a collection box from the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Collection box successfully deleted")
    })
    public ResponseEntity<Void> deleteCollectionBox(@PathVariable Long boxId) {
        collectionBoxService.removeCollectionBox(boxId);
        return ResponseEntity.noContent().build();
    }
}

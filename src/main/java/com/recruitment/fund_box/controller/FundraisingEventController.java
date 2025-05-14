package com.recruitment.fund_box.controller;

import com.recruitment.fund_box.dto.request.AssignCollectionBoxRequest;
import com.recruitment.fund_box.dto.request.FundraisingEventRequest;
import com.recruitment.fund_box.dto.response.FinancialReportResponse;
import com.recruitment.fund_box.dto.response.FundraisingEventResponse;
import com.recruitment.fund_box.service.CollectionBoxService;
import com.recruitment.fund_box.service.FundraisingEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@Tag(name = "Fundraising Event", description = "Fundraising Event management APIs")
public class FundraisingEventController {
    private final FundraisingEventService fundraisingEventService;
    private final CollectionBoxService collectionBoxService;

    public FundraisingEventController(
            FundraisingEventService fundraisingEventService,
            CollectionBoxService collectionBoxService) {
        this.fundraisingEventService = fundraisingEventService;
        this.collectionBoxService = collectionBoxService;
    }

    @GetMapping("/{eventId}")
    @Operation(summary = "Get event by ID", description = "Retrieves a fundraising event by its ID")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the event",
            content = @Content(
                schema = @Schema(implementation = FundraisingEventResponse.class),
                examples = @ExampleObject(value = """
                    {
                      "id": 1,
                      "name": "Annual Charity Gala",
                      "accountCurrency": "EUR",
                      "boxes": [
                        {
                          "id": 2,
                          "isAssigned": true,
                          "isEmpty": false
                        }
                      ]
                    }""")
            )
        )
    })
    public ResponseEntity<FundraisingEventResponse> getEventById(@PathVariable Long eventId) {
        FundraisingEventResponse response = fundraisingEventService.getFundraisingEvent(eventId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Create a new fundraising event", description = "Creates a new fundraising event with the provided details")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Event successfully created",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = FundraisingEventResponse.class),
                examples = @ExampleObject(
                    value = """
                    {
                    "id": 1,
                    "name": "Annual Charity Gala",
                    "accountCurrency": "EUR",
                    "boxes": []
                    }"""
                )
            )
        )
    })
    public ResponseEntity<FundraisingEventResponse> createFundraisingEvent(
            @Valid @RequestBody FundraisingEventRequest fundraisingEventRequest) {
        FundraisingEventResponse response = fundraisingEventService.createFundraisingEvent(fundraisingEventRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/report")
    @Operation(summary = "Get financial report", description = "Retrieves a financial report for all fundraising events")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the financial report")
    })
    public ResponseEntity<List<FinancialReportResponse>> getFinancialReport() {
        List<FinancialReportResponse> report = fundraisingEventService.getFinancialReport();
        return ResponseEntity.ok(report);
    }

    @PostMapping("/{eventId}/assign")
    @Operation(summary = "Assign collection box to event", description = "Assigns a collection box to a fundraising event")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Collection box successfully assigned to event")
    })
    public ResponseEntity<Void> assignCollectionBox(
            @PathVariable Long eventId,
            @Valid @RequestBody AssignCollectionBoxRequest request) {
        collectionBoxService.assignCollectionBox(request.boxId(), eventId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

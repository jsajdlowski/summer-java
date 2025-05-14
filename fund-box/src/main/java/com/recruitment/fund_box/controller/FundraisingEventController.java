package com.recruitment.fund_box.controller;

import com.recruitment.fund_box.dto.request.AssignCollectionBoxRequest;
import com.recruitment.fund_box.dto.request.FundraisingEventRequest;
import com.recruitment.fund_box.dto.response.FinancialReportResponse;
import com.recruitment.fund_box.dto.response.FundraisingEventResponse;
import com.recruitment.fund_box.service.CollectionBoxService;
import com.recruitment.fund_box.service.FundraisingEventService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
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
    public ResponseEntity<FundraisingEventResponse> getEventById(@PathVariable Long eventId) {
        FundraisingEventResponse response = fundraisingEventService.getFundraisingEvent(eventId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<FundraisingEventResponse> createFundraisingEvent(
            @Valid @RequestBody FundraisingEventRequest fundraisingEventRequest) {
        FundraisingEventResponse response = fundraisingEventService.createFundraisingEvent(fundraisingEventRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/report")
    public ResponseEntity<List<FinancialReportResponse>> getFinancialReport() {
        List<FinancialReportResponse> report = fundraisingEventService.getFinancialReport();
        return ResponseEntity.ok(report);
    }

    @PostMapping("/{eventId}/assign")
    public ResponseEntity<Void> assignCollectionBox(
            @PathVariable Long eventId,
            @Valid @RequestBody AssignCollectionBoxRequest request) {
        collectionBoxService.assignCollectionBox(request.boxId(), eventId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

package com.recruitment.fund_box.controller;

import com.recruitment.fund_box.dto.request.FundraisingEventRequest;
import com.recruitment.fund_box.dto.response.FinancialReportResponse;
import com.recruitment.fund_box.dto.response.FundraisingEventResponse;
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

    public FundraisingEventController(FundraisingEventService fundraisingEventService) {
        this.fundraisingEventService = fundraisingEventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FundraisingEventResponse> getEventById(@PathVariable Long id) {
        FundraisingEventResponse response = fundraisingEventService.getFundraisingEvent(id);
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
}

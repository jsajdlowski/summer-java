package com.recruitment.fund_box.service;

import com.recruitment.fund_box.dto.request.FundraisingEventRequest;
import com.recruitment.fund_box.dto.response.FundraisingEventResponse;
import com.recruitment.fund_box.dto.response.FinancialReportResponse;

import java.util.List;

public interface FundraisingEventService {
    FundraisingEventResponse createFundraisingEvent(FundraisingEventRequest fundraisingEventRequest);
    FundraisingEventResponse getFundraisingEvent(Long eventId);
    List<FinancialReportResponse> getFinancialReport();
}

package com.recruitment.fund_box.service;

import com.recruitment.fund_box.dto.request.FundraisingEventRequest;
import com.recruitment.fund_box.dto.response.FundraisingEventResponse;


public interface FundraisingEventService {
    FundraisingEventResponse createFundraisingEvent(FundraisingEventRequest fundraisingEventRequest);
    FundraisingEventResponse getFundraisingEvent(Long eventId);
}

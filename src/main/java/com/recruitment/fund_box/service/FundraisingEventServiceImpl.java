package com.recruitment.fund_box.service;

import com.recruitment.fund_box.domain.FundraisingEvent;
import com.recruitment.fund_box.dto.request.FundraisingEventRequest;
import com.recruitment.fund_box.dto.response.FundraisingEventResponse;
import com.recruitment.fund_box.dto.response.FinancialReportResponse;
import com.recruitment.fund_box.exceptions.ResourceNotFoundException;
import com.recruitment.fund_box.mappers.FundraisingEventMapper;
import com.recruitment.fund_box.repository.FundraisingEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FundraisingEventServiceImpl implements FundraisingEventService{
    private FundraisingEventRepository fundraisingEventRepository;
    private FundraisingEventMapper fundraisingEventMapper;

    public FundraisingEventServiceImpl(
            FundraisingEventRepository fundraisingEventRepository,
            FundraisingEventMapper fundraisingEventMapper){
        this.fundraisingEventRepository = fundraisingEventRepository;
        this.fundraisingEventMapper = fundraisingEventMapper;
    }

    public FundraisingEventResponse createFundraisingEvent(FundraisingEventRequest fundraisingEventRequest){
        FundraisingEvent event = new FundraisingEvent();
        event.setName(fundraisingEventRequest.name());
        event.setAccountCurrency(fundraisingEventRequest.accountCurrency());
        return fundraisingEventMapper.eventToEventDto(fundraisingEventRepository.save(event));
    }

    public FundraisingEventResponse getFundraisingEvent(Long eventId){
        FundraisingEvent event = fundraisingEventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID " + eventId));
        return fundraisingEventMapper.eventToEventDto(event);
    }

    @Override
    public List<FinancialReportResponse> getFinancialReport() {
        return fundraisingEventRepository.findAll().stream()
                .map(event -> new FinancialReportResponse(
                    event.getName(),
                    event.getFunds(),
                    event.getAccountCurrency()
                ))
                .collect(Collectors.toList());
    }
}

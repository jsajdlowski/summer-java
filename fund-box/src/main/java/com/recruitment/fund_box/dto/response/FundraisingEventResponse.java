package com.recruitment.fund_box.dto.response;

import com.recruitment.fund_box.enums.Currency;

import java.util.List;

public record FundraisingEventResponse(
        Long id,
        String name,
        Currency accountCurrency,
        List<CollectionBoxResponse> boxes
) {}

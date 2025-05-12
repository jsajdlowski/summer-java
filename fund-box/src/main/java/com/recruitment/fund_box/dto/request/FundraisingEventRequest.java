package com.recruitment.fund_box.dto.request;

import com.recruitment.fund_box.enums.Currency;

public record FundraisingEventRequest(
        String name,
        Currency accountCurrency
) {}

package com.recruitment.fund_box.dto.request;

import com.recruitment.fund_box.enums.Currency;
import java.math.BigDecimal;

public record DonationRequest(
    Currency currency,
    BigDecimal amount
) {} 
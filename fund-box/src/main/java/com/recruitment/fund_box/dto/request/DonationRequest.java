package com.recruitment.fund_box.dto.request;

import com.recruitment.fund_box.enums.Currency;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DonationRequest(
    @NotNull(message = "Currency is required")
    Currency currency,
    
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    BigDecimal amount
) {} 
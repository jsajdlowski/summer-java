package com.recruitment.fund_box.dto.request;

import com.recruitment.fund_box.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Request to process a donation (add money to collection box)")
public record DonationRequest(
    @Schema(
        description = "Currency of the donation",
        example = "USD",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Currency is required")
    Currency currency,
    
    @Schema(
        description = "Amount of the donation",
        example = "50.55",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    BigDecimal amount
) {}
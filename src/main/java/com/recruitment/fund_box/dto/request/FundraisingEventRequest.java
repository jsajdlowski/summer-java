package com.recruitment.fund_box.dto.request;

import com.recruitment.fund_box.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Request to create a fundraising event")
public record FundraisingEventRequest(
    @Schema(
        description = "Name of the fundraising event",
        example = "Annual Charity Gala",
        minLength = 3,
        maxLength = 100,
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Fundraising event name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    String name,
    
    @Schema(
        description = "Set currency for the event's account",
        example = "EUR",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Account currency is required")
    Currency accountCurrency
) {}
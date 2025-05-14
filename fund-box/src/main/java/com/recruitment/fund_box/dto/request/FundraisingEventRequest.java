package com.recruitment.fund_box.dto.request;

import com.recruitment.fund_box.enums.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FundraisingEventRequest(
        @NotBlank(message = "Fundraising event name is required")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        String name,
        
        @NotNull(message = "Account currency is required")
        Currency accountCurrency
) {}

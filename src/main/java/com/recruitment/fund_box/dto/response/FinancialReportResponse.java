package com.recruitment.fund_box.dto.response;

import com.recruitment.fund_box.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Financial report of fundraising events")
public record FinancialReportResponse(
    @Schema(description = "Name of the fundraising event", example = "MrBeast Charity Event")
    String eventName,
    
    @Schema(description = "Total amount raised", example = "1250.75")
    BigDecimal amount,
    
    @Schema(description = "Currency of the event account", example = "GBP")
    Currency currency
) {}
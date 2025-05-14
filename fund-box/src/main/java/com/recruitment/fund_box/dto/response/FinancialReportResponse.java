package com.recruitment.fund_box.dto.response;

import com.recruitment.fund_box.enums.Currency;
import java.math.BigDecimal;

public record FinancialReportResponse(
    String eventName,
    BigDecimal amount,
    Currency currency
) {} 
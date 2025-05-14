package com.recruitment.fund_box.dto.response;

import com.recruitment.fund_box.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Complete fundraising event details")
public record FundraisingEventResponse(
    @Schema(description = "Unique identifier of the event", example = "789")
    Long id,
    
    @Schema(description = "Name of the event", example = "Summer Fundraiser")
    String name,
    
    @Schema(description = "Default currency for the event", example = "USD")
    Currency accountCurrency,
    
    @Schema(description = "List of collection boxes assigned to this event")
    List<CollectionBoxResponse> boxes
) {}
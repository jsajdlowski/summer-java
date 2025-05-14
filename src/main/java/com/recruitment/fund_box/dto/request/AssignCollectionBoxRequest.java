package com.recruitment.fund_box.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request to assign a collection box to an event")
public record AssignCollectionBoxRequest(
    @Schema(
        description = "Unique identifier of the collection box",
        example = "1",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Collection box ID is required")
    Long boxId
) {}
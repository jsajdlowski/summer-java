package com.recruitment.fund_box.dto.request;

import jakarta.validation.constraints.NotNull;

public record AssignCollectionBoxRequest(
    @NotNull(message = "Collection box ID is required")
    Long boxId
) {} 
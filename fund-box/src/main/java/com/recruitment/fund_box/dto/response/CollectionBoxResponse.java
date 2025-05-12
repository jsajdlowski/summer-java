package com.recruitment.fund_box.dto.response;

public record CollectionBoxResponse(
        Long id,
        boolean isAssigned,
        boolean isEmpty
) {}

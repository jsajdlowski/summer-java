package com.recruitment.fund_box.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Collection box information")
public record CollectionBoxResponse(
    @Schema(description = "Unique identifier of the box", example = "1")
    Long id,
    
    @Schema(description = "Whether the box is assigned to an event", example = "true")
    boolean isAssigned,
    
    @Schema(description = "Whether the box contains any donations", example = "false")
    boolean isEmpty
) {}
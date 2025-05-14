package com.recruitment.fund_box.mappers;
import com.recruitment.fund_box.domain.FundraisingEvent;
import com.recruitment.fund_box.dto.response.FundraisingEventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = CollectionBoxMapper.class)
public interface FundraisingEventMapper {
    @Mapping(source = "collectionBoxes", target = "boxes")
    FundraisingEventResponse eventToEventDto(FundraisingEvent fundraisingEvent);
}

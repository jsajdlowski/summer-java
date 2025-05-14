package com.recruitment.fund_box.mappers;
import com.recruitment.fund_box.domain.CollectionBox;
import com.recruitment.fund_box.dto.response.CollectionBoxResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionBoxMapper {

    @Mapping(target = "isAssigned", expression = "java(collectionBox.getFundraisingEvent() != null)")
    @Mapping(target = "isEmpty", expression = "java(collectionBox.isEmpty())")
    CollectionBoxResponse collectionBoxToCollectionBoxDto(CollectionBox collectionBox);

    List<CollectionBoxResponse> collectionBoxesToCollectionBoxResponses(List<CollectionBox> collectionBoxes);
}

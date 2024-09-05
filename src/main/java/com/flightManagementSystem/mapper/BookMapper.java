package com.flightManagementSystem.mapper;

import com.flightManagementSystem.dto.*;
import com.flightManagementSystem.entity.Book;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    Book requestToEntity(BookRequest bookRequest);

    BookDto entityToDto(Book entity);

    void updateEntityFromDto(BookDto dto, @MappingTarget Book entity);
}

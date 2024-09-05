package com.flightManagementSystem.mapper;

import com.flightManagementSystem.dto.*;
import com.flightManagementSystem.entity.Flight;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FlightMapper {

    @Mapping(target = "id", ignore = true)
    Flight requestToEntity(FlightRequest bookRequest);

    FlightDto entityToDto(Flight entity);

    void updateEntityFromDto(FlightDto dto, @MappingTarget Flight entity);
}

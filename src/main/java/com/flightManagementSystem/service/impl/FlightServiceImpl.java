package com.flightManagementSystem.service.impl;

import com.flightManagementSystem.dto.FlightDto;
import com.flightManagementSystem.dto.FlightRequest;
import com.flightManagementSystem.mapper.FlightMapper;
import com.flightManagementSystem.repository.FlightRepository;
import com.flightManagementSystem.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    FlightMapper flightMapper;
    FlightRepository flightRepository;

    @Override
    public FlightDto save(FlightRequest flightRequest) {
        return flightMapper.entityToDto(
                flightRepository.save(
                        flightMapper.requestToEntity(
                                flightRequest
                        )));
    }

    @Override
    @Cacheable(value = "flights", key = "'allFlights'")
    public Page<FlightDto> getAllFlights(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return flightRepository.findAllByIsEnabledTrue(pageable)
                .map(flightMapper::entityToDto);
    }

    @Override
    @Cacheable(value = "flights", key = "'allFlightsIn24Hours'")
    public Page<FlightDto> getAllFlightsIn24Hours(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return null;
    }

    @Override
    public Page<FlightDto> searchFlight(String destination, ZonedDateTime datetime, int tickets) {
        return null;
    }

    @Override
    public FlightDto getFlightById(Long id) {
        return null;
    }

    @Override
    public FlightDto updateFlight(FlightRequest flightRequest) {
        return null;
    }

    @Override
    public void deleteFlight(Long id) {

    }
}

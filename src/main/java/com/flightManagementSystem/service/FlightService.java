package com.flightManagementSystem.service;

import com.flightManagementSystem.dto.FlightDto;
import com.flightManagementSystem.dto.FlightRequest;
import org.springframework.data.domain.Page;

import java.time.ZonedDateTime;

public interface FlightService {

    FlightDto save(FlightRequest flightRequest);

    Page<FlightDto> getAllFlights(int pageSize, int pageNumber);

    Page<FlightDto> getAllFlightsIn24Hours(int pageSize, int pageNumber);

    Page<FlightDto> searchFlight(String destination, ZonedDateTime datetime, int tickets);

    FlightDto getFlightById(Long id);

    FlightDto updateFlight(FlightRequest flightRequest);

    void deleteFlight(Long id);
}

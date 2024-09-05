package com.flightManagementSystem.service.impl;

import com.flightManagementSystem.dto.FlightDto;
import com.flightManagementSystem.dto.FlightRequest;
import com.flightManagementSystem.entity.Flight;
import com.flightManagementSystem.mapper.FlightMapper;
import com.flightManagementSystem.repository.FlightRepository;
import com.flightManagementSystem.service.FlightService;
import com.flightManagementSystem.specification.FlightSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<FlightDto> findAllByDatetimeBetweenAndIsEnabledTrue(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return flightRepository.findAllByDatetimeBetweenAndIsEnabledTrue(
                ZonedDateTime.now(), ZonedDateTime.now().plusHours(24), pageable)
                .map(flightMapper::entityToDto);
    }

    @Override
    @Cacheable(value = "flights", key = "'searchFlight'")
    public Page<FlightDto> searchFlight(String destination, ZonedDateTime datetime, int tickets,
                                        int pageNumber, int pageSize) {
        Specification<Flight> specification = FlightSpecification.searchFlightByCriteria(
                destination, datetime, tickets);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return flightRepository.findAllByIsEnabledTrue(specification, pageable)
                .map(flightMapper::entityToDto);
    }

    @Override
    @Cacheable(value = "flights", key = "'flightById_' + #id")
    public FlightDto getFlightById(Long id) {
        return flightMapper.entityToDto(
                findById(id)
        );
    }

    @Override
    @Transactional
    @CachePut(value = "flights", key = "'flightById_' + #flightDto.id")
    public FlightDto updateFlight(FlightDto flightDto) {
        flightMapper.updateEntityFromDto(flightDto, findById(flightDto.getId()));
        return flightMapper.entityToDto(
                flightRepository.save(
                        findById(
                                flightDto.getId()
                        )));
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "flights", key = "'allFlights'", allEntries = true),
            @CacheEvict(value = "flights", key = "'flightById_' + #id")
    })
    public void deleteFlight(Long id) {
        findById(id).setIsEnabled(false);
        flightRepository.save(
                findById(id)
        );
    }

    private Flight findById(Long id) {
        return flightRepository.findByIdAndIsEnabledTrue(id)
                .orElseThrow(() -> new NumberFormatException("Author not found with id: " + id));
    }
}

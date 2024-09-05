package com.flightManagementSystem.repository;

import com.flightManagementSystem.entity.Flight;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;

import java.time.ZonedDateTime;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {
    Optional<Flight> findByIdAndIsEnabledTrue(Long id);

    Page<Flight> findAllByIsEnabledTrue(Pageable pageable);

    Page<Flight> findAllByDatetimeBetweenAndIsEnabledTrue(
            ZonedDateTime start, ZonedDateTime end, Pageable pageable);

    Page<Flight> findAllByIsEnabledTrue(Specification<Flight> specification, Pageable pageable);
}

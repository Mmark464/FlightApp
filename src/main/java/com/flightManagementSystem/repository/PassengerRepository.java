package com.flightManagementSystem.repository;

import com.flightManagementSystem.entity.Passenger;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long>, JpaSpecificationExecutor<Passenger> {
    Optional<Passenger> findByIdAndIsEnabledTrue(Long id);
    Page<Passenger> findAllByIsEnabledTrue(Pageable pageable);
}

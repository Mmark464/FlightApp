package com.flightManagementSystem.repository;

import com.flightManagementSystem.entity.Booking;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
    Optional<Booking> findByIdAndIsEnabledTrue(Long id);
    Page<Booking> findAllByIsEnabledTrue(Pageable pageable);
}

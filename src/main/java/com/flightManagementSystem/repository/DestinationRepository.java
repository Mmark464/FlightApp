package com.flightManagementSystem.repository;

import com.flightManagementSystem.entity.Destination;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long>, JpaSpecificationExecutor<Destination> {
    Optional<Destination> findByIdAndIsEnabledTrue(Long id);
    Page<Destination> findAllByIsEnabledTrue(Pageable pageable);
}

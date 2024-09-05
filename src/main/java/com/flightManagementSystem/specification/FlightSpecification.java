package com.flightManagementSystem.specification;

import com.flightManagementSystem.entity.Flight;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;

public class FlightSpecification {

    public static Specification<Flight> searchFlightByCriteria(String destination,
                                                             ZonedDateTime datetime,
                                                             int tickets) {
        return (root, _, cb) -> {
            Predicate predicate = cb.conjunction();
            if(destination != null) {
                predicate = cb.and(predicate,
                cb.equal(root.get("destination").get("name"),destination));
            }
            if(datetime != null) {
                predicate = cb.and(predicate,
                        cb.greaterThanOrEqualTo(root.get("datetime"), datetime));
            }
            if(tickets > 0) {
                predicate = cb.and(predicate,
                        cb.greaterThanOrEqualTo(root.get("emptySeats"), tickets));
            }
            return predicate;
        };
    }
}

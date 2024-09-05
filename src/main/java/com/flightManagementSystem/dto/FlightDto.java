package com.flightManagementSystem.dto;

import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto implements Serializable {
    private Long id;
    private ZonedDateTime datetime;
    private String destination;
    private int emptySeats;
}

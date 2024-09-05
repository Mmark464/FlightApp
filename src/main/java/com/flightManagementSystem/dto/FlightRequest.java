package com.flightManagementSystem.dto;


import lombok.*;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightRequest {
    private ZonedDateTime datetime;
    private String destination;
    private int emptySeats;
}

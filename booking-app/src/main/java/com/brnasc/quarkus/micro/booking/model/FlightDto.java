package com.brnasc.quarkus.micro.booking.model;

import lombok.Data;

@Data
public class FlightDto {

    private Long id;

    private Long bookingId;

    private String flightFrom;

    private String flightTo;
}

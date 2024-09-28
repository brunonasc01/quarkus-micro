package com.brnasc.quarkus.micro.booking.service;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Readiness
public class ReadinessCheck implements HealthCheck {

    @RestClient
    FlightService flightService;

    @RestClient
    HotelService hotelService;

    @Override
    public HealthCheckResponse call() {
            if(flightService.health().getStatus() == HealthCheckResponse.Status.UP) {
            return HealthCheckResponse.up("ready");
        }
        return HealthCheckResponse.down("unavailable");
    }
}

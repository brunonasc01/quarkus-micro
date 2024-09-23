package com.brnasc.quarkus.micro.booking.service;

import com.brnasc.quarkus.micro.booking.model.FlightDto;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:8081/flight")
public interface FlightService {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public FlightDto retrieveFlight(@PathParam("id") Long id);

    @GET
    @Path("/booking/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public FlightDto retrieveByBooking(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FlightDto createFlight(FlightDto flight);
}

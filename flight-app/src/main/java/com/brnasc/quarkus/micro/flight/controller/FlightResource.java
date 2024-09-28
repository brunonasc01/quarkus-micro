package com.brnasc.quarkus.micro.flight.controller;

import com.brnasc.quarkus.micro.flight.model.Flight;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("flight")
public class FlightResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Flight> flights() {
    return Flight.listAll();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Flight retrieveFlight(@PathParam("id") Long id) {
    return Flight.findById(id);
  }

  @GET
  @Path("/booking/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Flight retrieveByBooking(@PathParam("id") Long id) {
      return Flight.findByBookingId(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Flight createFlight(Flight flight) {
    flight.persist();
    return flight;
  }
}

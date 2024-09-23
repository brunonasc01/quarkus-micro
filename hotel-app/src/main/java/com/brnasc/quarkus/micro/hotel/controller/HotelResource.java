package com.brnasc.quarkus.micro.hotel.controller;

import com.brnasc.quarkus.micro.hotel.model.Hotel;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("hotel")
public class HotelResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Hotel> hotels() {
    return Hotel.listAll();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Hotel retrieveHotel(@PathParam("id") Long id) {
    return Hotel.findById(id);
  }

  @GET
  @Path("/booking/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Hotel retrieveByBooking(@PathParam("id") Long id) {
    return Hotel.findByBookingId(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Hotel createHotel(Hotel hotel) {
    hotel.persist();
    return hotel;
  }
}

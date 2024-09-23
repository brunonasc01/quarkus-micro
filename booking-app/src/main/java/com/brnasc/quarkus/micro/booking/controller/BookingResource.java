package com.brnasc.quarkus.micro.booking.controller;

import com.brnasc.quarkus.micro.booking.model.Booking;
import com.brnasc.quarkus.micro.booking.model.BookingDto;
import com.brnasc.quarkus.micro.booking.model.FlightDto;
import com.brnasc.quarkus.micro.booking.model.HotelDto;
import com.brnasc.quarkus.micro.booking.service.FlightService;
import com.brnasc.quarkus.micro.booking.service.HotelService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("booking")
public class BookingResource {

  @Inject
    @RestClient
  FlightService flightService;

  @Inject
  @RestClient
  HotelService hotelService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<BookingDto> bookings() {
    return Booking.<Booking>listAll().stream()
        .map(
            booking ->
                BookingDto.of(
                    booking,
                        flightService.retrieveByBooking(booking.id),
                        hotelService.retrieveByBooking(booking.id)))
        .toList();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Booking retrieveBooking(@PathParam("id") Long id) {
    return Booking.findById(id);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Booking createBooking(BookingDto bookingDto) {
    Booking booking = new Booking();
    booking.persist();

    FlightDto flight = new FlightDto();
    flight.setFlightFrom(bookingDto.getFromAirport());
    flight.setFlightTo(bookingDto.getToAirport());
    flight.setBookingId(booking.id);
    flightService.createFlight(flight);

    HotelDto hotel = new HotelDto();
    hotel.setNights(bookingDto.getNights());
    hotel.setBookingId(booking.id);
    hotelService.createHotel(hotel);

    return booking;
  }
}

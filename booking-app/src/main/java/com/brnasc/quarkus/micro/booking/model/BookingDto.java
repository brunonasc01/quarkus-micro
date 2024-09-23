package com.brnasc.quarkus.micro.booking.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

  private String fromAirport;

  private String toAirport;

  private Integer nights;

  public static BookingDto of(Booking booking, FlightDto flight, HotelDto hotel) {

    if(flight == null) {
      flight = new FlightDto();
    }

    if(hotel == null) {
      hotel = new HotelDto();
    }

    return new BookingDto(flight.getFlightFrom(), flight.getFlightTo(), hotel.getNights());
  }

  public static BookingDto of(String fromAirport, String toAirport, Integer nights) {
    return new BookingDto(fromAirport, toAirport, nights);
  }
}

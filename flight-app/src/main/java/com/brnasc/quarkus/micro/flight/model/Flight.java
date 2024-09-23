package com.brnasc.quarkus.micro.flight.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "flight_sequence", sequenceName = "flight_sequence", allocationSize = 1)
public class Flight extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_sequence")
  public Long id;

  public Long bookingId;

  public String flightFrom;

  public String flightTo;

  public static Flight findByBookingId(Long bookingId) {
    return find("bookingId", bookingId).firstResult();
  }
}

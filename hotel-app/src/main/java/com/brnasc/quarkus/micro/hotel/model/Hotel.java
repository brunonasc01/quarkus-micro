package com.brnasc.quarkus.micro.hotel.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "hotel_sequence", sequenceName = "hotel_sequence", allocationSize = 1)
public class Hotel extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_sequence")
  public Long id;

  public Long bookingId;

  public Integer nights;

  public static Hotel findByBookingId(Long bookingId) {
    return find("bookingId", bookingId).firstResult();
  }
}

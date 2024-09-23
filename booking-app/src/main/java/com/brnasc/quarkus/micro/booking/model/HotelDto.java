package com.brnasc.quarkus.micro.booking.model;

import lombok.Data;

@Data
public class HotelDto {

    private Long id;

    private Long bookingId;

    private Integer nights;
}

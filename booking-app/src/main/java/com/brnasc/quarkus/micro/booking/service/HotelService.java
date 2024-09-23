package com.brnasc.quarkus.micro.booking.service;

import com.brnasc.quarkus.micro.booking.model.HotelDto;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8082/hotel")
public interface HotelService {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HotelDto retrieveHotel(@PathParam("id") Long id);

    @GET
    @Path("/booking/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HotelDto retrieveByBooking(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public HotelDto createHotel(HotelDto hotel);
}

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
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.temporal.ChronoUnit;
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
    @Timeout(unit = ChronoUnit.SECONDS, value = 2)
    @Fallback(fallbackMethod = "fallBack")
    @CircuitBreaker(
            requestVolumeThreshold = 4, //amostragem para verificacao do circuit breaker
            failureRatio = 0.5, //porcenta de falha para abrir o circuito
            delay = 5000, //atraso antes de tentar fechar o circuito novamente
            successThreshold = 2 //numero de requisicoes com sucesso para considerar resolvido
    )
    public FlightDto retrieveByBooking(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FlightDto createFlight(FlightDto flight);

    default public FlightDto fallBack(Long id) {
        return new FlightDto();
    }
}

package com.ulgekadir.reservationservice.controllers;

import com.ulgekadir.reservationservice.dtos.requests.CreateReservationRequest;
import com.ulgekadir.reservationservice.dtos.requests.UpdateReservationRequest;
import com.ulgekadir.reservationservice.dtos.responses.CreateReservationResponse;
import com.ulgekadir.reservationservice.dtos.responses.GetAllReservationsResponse;
import com.ulgekadir.reservationservice.dtos.responses.GetReservationResponse;
import com.ulgekadir.reservationservice.dtos.responses.UpdateReservationResponse;
import com.ulgekadir.reservationservice.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationsController {
    private final ReservationService service;

    @GetMapping
    public List<GetAllReservationsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetReservationResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateReservationResponse add(@Valid @RequestBody CreateReservationRequest request) throws InterruptedException {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateReservationResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateReservationRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
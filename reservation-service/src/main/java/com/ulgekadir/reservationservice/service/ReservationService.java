package com.ulgekadir.reservationservice.service;

import com.ulgekadir.commonpackage.exceptions.BusinessException;
import com.ulgekadir.commonpackage.utils.constants.Messages;
import com.ulgekadir.commonpackage.utils.mappers.ModelMapperService;
import com.ulgekadir.reservationservice.clients.FacilityClient;
import com.ulgekadir.reservationservice.dtos.requests.CreateReservationRequest;
import com.ulgekadir.reservationservice.dtos.requests.UpdateReservationRequest;
import com.ulgekadir.reservationservice.dtos.responses.CreateReservationResponse;
import com.ulgekadir.reservationservice.dtos.responses.GetAllReservationsResponse;
import com.ulgekadir.reservationservice.dtos.responses.GetReservationResponse;
import com.ulgekadir.reservationservice.dtos.responses.UpdateReservationResponse;
import com.ulgekadir.reservationservice.entities.Reservation;
import com.ulgekadir.reservationservice.repository.ReservationRepository;
import com.ulgekadir.reservationservice.service.rules.ReservationBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository repository;
    private final ModelMapperService mapper;
    private final ReservationBusinessRules rules;

    public List<GetAllReservationsResponse> getAll() {
        List<Reservation>reservations = repository.findAll();
        List<GetAllReservationsResponse> response = reservations
                .stream()
                .map(reservation -> mapper.forResponse().map(reservation, GetAllReservationsResponse.class))
                .toList();
        return response;
    }

    public GetReservationResponse getById(UUID id) {
        Reservation reservation = repository.findById(id).orElseThrow(()->new BusinessException(Messages.Reservation.NotExists));
        GetReservationResponse response = mapper.forResponse().map(reservation, GetReservationResponse.class);
        return response;
    }

    public CreateReservationResponse add(CreateReservationRequest request) throws InterruptedException {
        rules.ensureFacilityIsAvailable(request.getFacilityId());
        Reservation reservation = mapper.forRequest().map(request, Reservation.class);
        reservation.setId(UUID.randomUUID());
        reservation.setTotalPrice(getTotalPrice(reservation));
        reservation.setReservedAt(LocalDate.now());
        Reservation createdReservation = repository.save(reservation);
        CreateReservationResponse response = mapper.forResponse().map(createdReservation, CreateReservationResponse.class);
        return response;
    }

    public UpdateReservationResponse update(UUID id, UpdateReservationRequest request) {
        rules.checkIfReservationExists(id);
        Reservation reservation = mapper.forRequest().map(request, Reservation.class);
        reservation.setId(id);
        Reservation updatedReservation =repository.save(reservation);
        UpdateReservationResponse response = mapper.forResponse().map(updatedReservation, UpdateReservationResponse.class);
        return response;
    }

    public void delete(UUID id) {
        rules.checkIfReservationExists(id);
        repository.deleteById(id);
    }

    private BigDecimal getTotalPrice(Reservation reservation) {
        BigDecimal hourlyRate = reservation.getHourlyRate();
        BigDecimal reservedForHours = BigDecimal.valueOf(reservation.getReservedForHours());
        return hourlyRate.multiply(reservedForHours);
    }
}

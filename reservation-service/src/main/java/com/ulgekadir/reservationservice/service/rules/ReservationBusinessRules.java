package com.ulgekadir.reservationservice.service.rules;

import com.ulgekadir.commonpackage.exceptions.BusinessException;
import com.ulgekadir.commonpackage.utils.constants.Messages;
import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import com.ulgekadir.reservationservice.clients.FacilityClient;
import com.ulgekadir.reservationservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationBusinessRules {
    private final ReservationRepository repository;
    private final FacilityClient facilityClient;

    public void ensureFacilityIsAvailable(UUID facilityId) throws InterruptedException {
        ClientResponse response = facilityClient.checkIfFacilityAvailable(facilityId);
        checkClientResponse(response);
    }

    public void checkIfReservationExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Reservation.NotExists);
        }
    }

    private void checkClientResponse(ClientResponse response) {
        if (!response.isSuccess()) {
            throw new BusinessException(response.getMessage());
        }
    }

}

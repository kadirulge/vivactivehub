package com.ulgekadir.reservationservice.service.rules;

import com.ulgekadir.commonpackage.exceptions.BusinessException;
import com.ulgekadir.commonpackage.utils.constants.Messages;
import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import com.ulgekadir.commonpackage.utils.dtos.CreateReservationPaymentRequest;
import com.ulgekadir.reservationservice.clients.FacilityClient;
import com.ulgekadir.reservationservice.clients.FilterClient;
import com.ulgekadir.reservationservice.clients.PaymentClient;
import com.ulgekadir.reservationservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationBusinessRules {
    private final ReservationRepository repository;
    private final PaymentClient paymentClient;

    public void checkIfReservationExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Reservation.NotExists);
        }
    }

    public void checkClientResponse(ClientResponse response) {
        if (!response.isSuccess()) {
            throw new BusinessException(response.getMessage());
        }
    }

    public void ensurePaymentIsProcessed(CreateReservationPaymentRequest request) throws InterruptedException {
        ClientResponse response = paymentClient.processRentalPayment(request);
        if(!response.isSuccess())
        {
            throw new BusinessException(response.getMessage());
        }
    }

}

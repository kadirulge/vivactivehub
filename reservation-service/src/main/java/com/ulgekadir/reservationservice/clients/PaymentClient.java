package com.ulgekadir.reservationservice.clients;

import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import com.ulgekadir.commonpackage.utils.dtos.CreateReservationPaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-service", fallback = PaymentClientFallback.class)
public interface PaymentClient
{
    @PostMapping(value = "/api/payments/process-reservation-payment")
    ClientResponse processRentalPayment(CreateReservationPaymentRequest request) throws InterruptedException;
}

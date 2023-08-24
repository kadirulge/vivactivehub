package com.ulgekadir.reservationservice.clients;

import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import com.ulgekadir.commonpackage.utils.dtos.CreateReservationPaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentClientFallback implements PaymentClient
{
    @Override
    public ClientResponse processRentalPayment(CreateReservationPaymentRequest request) throws InterruptedException
    {
        log.info("PAYMENT SERVICE IS DOWN");
        throw new RuntimeException("PAYMENT DOWN");
    }
}

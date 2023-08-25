package com.ulgekadir.invoiceservice.service.kafka;

import com.ulgekadir.commonpackage.events.ReservationPaymentCreatedEvent;
import com.ulgekadir.commonpackage.utils.mappers.ModelMapperService;
import com.ulgekadir.invoiceservice.entities.Invoice;
import com.ulgekadir.invoiceservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationConsumer {
    private final InvoiceService service;
    private final ModelMapperService mapper;

    @KafkaListener
            (
                    topics = "reservation-payment-created",
                    groupId = "reservation-payment-create"
            )
    public void consume(ReservationPaymentCreatedEvent event)
    {
        System.out.println(event.getCategoryName()+event.getInstitutionName());
        Invoice invoice = mapper.forRequest().map(event, Invoice.class);
        System.out.println(invoice.getCategoryName()+invoice.getInstitutionName());
        service.add(invoice);
        log.info("Reservation created event consumed {}", event);
    }
}

package com.ulgekadir.paymentservice.controllers;

import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import com.ulgekadir.commonpackage.utils.dtos.CreateReservationPaymentRequest;
import com.ulgekadir.paymentservice.dtos.requests.CreatePaymentRequest;
import com.ulgekadir.paymentservice.dtos.requests.UpdatePaymentRequest;
import com.ulgekadir.paymentservice.dtos.responses.CreatePaymentResponse;
import com.ulgekadir.paymentservice.dtos.responses.GetAllPaymentsResponse;
import com.ulgekadir.paymentservice.dtos.responses.GetPaymentResponse;
import com.ulgekadir.paymentservice.dtos.responses.UpdatePaymentResponse;
import com.ulgekadir.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/payments")
public class PaymentsController
{
    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable UUID id)
    {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request)
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable UUID id, @Valid @RequestBody UpdatePaymentRequest request)
    {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id)
    {
        service.delete(id);
    }

    @PostMapping("/process-reservation-payment")
    public ClientResponse processReservationPayment(@RequestBody CreateReservationPaymentRequest request)
    {
        return service.processReservationPayment(request);
    }
}

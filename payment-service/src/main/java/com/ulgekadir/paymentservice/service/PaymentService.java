package com.ulgekadir.paymentservice.service;

import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import com.ulgekadir.commonpackage.utils.dtos.CreateReservationPaymentRequest;
import com.ulgekadir.commonpackage.utils.mappers.ModelMapperService;
import com.ulgekadir.paymentservice.dtos.requests.CreatePaymentRequest;
import com.ulgekadir.paymentservice.dtos.requests.UpdatePaymentRequest;
import com.ulgekadir.paymentservice.dtos.responses.CreatePaymentResponse;
import com.ulgekadir.paymentservice.dtos.responses.GetAllPaymentsResponse;
import com.ulgekadir.paymentservice.dtos.responses.GetPaymentResponse;
import com.ulgekadir.paymentservice.dtos.responses.UpdatePaymentResponse;
import com.ulgekadir.paymentservice.entities.Payment;
import com.ulgekadir.paymentservice.repository.PaymentRepository;
import com.ulgekadir.paymentservice.service.rules.PaymentBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final ModelMapperService mapper;
    private final PosService posService;
    private final PaymentBusinessRules rules;


    public List<GetAllPaymentsResponse> getAll()
    {
        List<Payment> payments = repository.findAll();

        List<GetAllPaymentsResponse> response =
                payments.stream().map(payment -> mapper.forResponse().map(payment, GetAllPaymentsResponse.class)).toList();
        return response;
    }


    public GetPaymentResponse getById(UUID id)
    {
        rules.checkIfPaymentExists(id);
        Payment payment = repository.findById(id).orElseThrow();

        GetPaymentResponse response = mapper.forResponse().map(payment, GetPaymentResponse.class);
        return response;
    }


    public CreatePaymentResponse add(CreatePaymentRequest request)
    {
        rules.checkIfCardExistsByCardNumber(request);

        Payment payment = mapper.forRequest().map(request, Payment.class);
        payment.setId(null);
        repository.save(payment);

        CreatePaymentResponse response = mapper.forResponse().map(payment, CreatePaymentResponse.class);
        return response;
    }


    public UpdatePaymentResponse update(UUID id, UpdatePaymentRequest request)
    {
        rules.checkIfPaymentExists(id);

        Payment payment = mapper.forRequest().map(request, Payment.class);
        payment.setId(id);
        repository.save(payment);

        UpdatePaymentResponse response = mapper.forResponse().map(payment, UpdatePaymentResponse.class);
        return response;
    }


    public void delete(UUID id)
    {
        rules.checkIfPaymentExists(id);

        repository.deleteById(id);
    }


    public ClientResponse processReservationPayment(CreateReservationPaymentRequest request)
    {
        ClientResponse response = new ClientResponse();
        validatePayment(request, response);
        return response;
    }

    private void validatePayment(CreateReservationPaymentRequest request, ClientResponse response)
    {
        try
        {
            rules.checkIfPaymentIsValid(request);
            Payment payment = repository.findByCardNumber(request.getCardNumber());

            rules.checkIfBalanceIsEnough(request.getPrice(), payment.getBalance());
            //FAKE POS SERVICE
            posService.pay();

            processPayment(payment, request.getPrice());
            response.setSuccess(true);
        }
        catch(Exception e)
        {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
    }


    public void processPayment(Payment payment, BigDecimal price) {
        BigDecimal newBalance = payment.getBalance().subtract(price);
        payment.setBalance(newBalance);
        repository.save(payment);
    }
}

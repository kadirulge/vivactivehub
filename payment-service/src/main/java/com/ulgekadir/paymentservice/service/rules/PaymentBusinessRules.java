package com.ulgekadir.paymentservice.service.rules;

import com.ulgekadir.commonpackage.exceptions.BusinessException;
import com.ulgekadir.commonpackage.utils.dtos.CreateReservationPaymentRequest;
import com.ulgekadir.paymentservice.dtos.requests.CreatePaymentRequest;
import com.ulgekadir.paymentservice.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentBusinessRules
{
    private final PaymentRepository repository;

    public void checkIfPaymentExists(UUID id)
    {
        if(!repository.existsById(id))
        {
            throw new BusinessException("PAYMENT_NOT_FOUND");
        }
    }

    public void checkIfCardExistsByCardNumber(CreatePaymentRequest request)
    {
        if(repository.existsByCardNumber(request.getCardNumber()))
        {
            throw new BusinessException("CARD_NUMBER_ALREADY_EXISTS");
        }
    }
    public void checkIfPaymentIsValid(CreateReservationPaymentRequest request)
    {
        if(!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv()
        ))
        {
            throw new BusinessException("NOT_VALID_PAYMENT");
        }
    }

    public void checkIfBalanceIsEnough(BigDecimal price, BigDecimal balance) {
        int comparisonResult = balance.compareTo(price);

        if (comparisonResult < 0) {
            throw new BusinessException("NOT_ENOUGH_MONEY");
        }
    }
}

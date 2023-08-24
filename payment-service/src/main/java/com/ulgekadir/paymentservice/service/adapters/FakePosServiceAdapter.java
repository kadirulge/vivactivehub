package com.ulgekadir.paymentservice.service.adapters;

import com.ulgekadir.paymentservice.service.PosService;
import org.springframework.stereotype.Service;

@Service
public class FakePosServiceAdapter implements PosService
{
    @Override
    public void pay()
    {
        boolean isPaymentSuccessfull = true; //new Random().nextBoolean();
        if(!isPaymentSuccessfull)
        {
            throw new RuntimeException("Payment invalid...");
        }
    }
}

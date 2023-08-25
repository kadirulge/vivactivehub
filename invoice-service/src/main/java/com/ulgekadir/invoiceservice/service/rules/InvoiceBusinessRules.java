package com.ulgekadir.invoiceservice.service.rules;

import com.ulgekadir.commonpackage.exceptions.BusinessException;
import com.ulgekadir.invoiceservice.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules
{
    private final InvoiceRepository repository;
    public void checkIfInvoiceExists(UUID id)
    {
        if(!repository.existsById(id))
        {
            throw new BusinessException("Messages.Invoice.NotFound");
        }
    }
}

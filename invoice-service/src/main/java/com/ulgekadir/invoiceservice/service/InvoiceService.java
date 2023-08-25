package com.ulgekadir.invoiceservice.service;

import com.ulgekadir.commonpackage.exceptions.BusinessException;
import com.ulgekadir.commonpackage.utils.constants.Messages;
import com.ulgekadir.commonpackage.utils.mappers.ModelMapperService;
import com.ulgekadir.invoiceservice.dtos.responses.GetAllInvoicesResponse;
import com.ulgekadir.invoiceservice.dtos.responses.GetInvoiceResponse;
import com.ulgekadir.invoiceservice.entities.Invoice;
import com.ulgekadir.invoiceservice.repository.InvoiceRepository;
import com.ulgekadir.invoiceservice.service.rules.InvoiceBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapperService mapper;
    private final InvoiceBusinessRules rules;


    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        List<GetAllInvoicesResponse> response =
                invoices.stream().map(invoice -> mapper.forResponse().map(invoice, GetAllInvoicesResponse.class)).toList();
        return response;
    }


    public GetInvoiceResponse getById(UUID id) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = repository.findById(id).orElseThrow( ()->new BusinessException(Messages.Invoice.NotExists));
        GetInvoiceResponse response = mapper.forResponse().map(invoice, GetInvoiceResponse.class);
        return response;
    }


    public void add(Invoice invoice) {
        repository.save(invoice);
    }
}
package com.ulgekadir.invoiceservice.controllers;

import com.ulgekadir.invoiceservice.dtos.responses.GetAllInvoicesResponse;
import com.ulgekadir.invoiceservice.dtos.responses.GetInvoiceResponse;
import com.ulgekadir.invoiceservice.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService service;
    @GetMapping
    public List<GetAllInvoicesResponse> getAll()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }
}

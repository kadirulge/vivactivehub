package com.ulgekadir.invoiceservice.repository;

import com.ulgekadir.invoiceservice.entities.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface InvoiceRepository  extends MongoRepository<Invoice, UUID> {
}

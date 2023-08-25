package com.ulgekadir.invoiceservice.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateInvoiceResponse
{
    private UUID id;
    private String cardHolder;
    private String categoryName;
    private String institutionName;
    private BigDecimal hourlyRate;
    private BigDecimal totalPrice;
    private int reservedForHours;
    private LocalDateTime reservedAt;
}

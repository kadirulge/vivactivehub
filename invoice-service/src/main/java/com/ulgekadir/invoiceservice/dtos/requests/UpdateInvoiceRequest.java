package com.ulgekadir.invoiceservice.dtos.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateInvoiceRequest
{
    @NotBlank
    private String cardHolder;
    @NotBlank
    private String categoryName;
    @NotBlank
    private String institutionName;
    @DecimalMin(value = "0.01")
    private BigDecimal hourlyRate;
    @Min(1)
    private int reservedForHours;
}

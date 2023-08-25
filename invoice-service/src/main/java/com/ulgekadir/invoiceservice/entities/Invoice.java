package com.ulgekadir.invoiceservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    private String id;
    private String cardHolder;
    private String categoryName;
    private String institutionName;
    private BigDecimal hourlyRate;
    private BigDecimal totalPrice;
    private int reservedForHours;
    private LocalDateTime reservedAt;
}

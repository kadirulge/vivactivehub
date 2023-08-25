package com.ulgekadir.commonpackage.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationPaymentCreatedEvent implements Event {
    private String cardHolder;
    private String categoryName;
    private String institutionName;
    private BigDecimal hourlyRate;
    private BigDecimal totalPrice;
    private int reservedForHours;
    private LocalDateTime reservedAt;
}

package com.ulgekadir.reservationservice.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetReservationResponse {
    private UUID id;
    private UUID facilityId;
    private BigDecimal hourlyRate;
    private BigDecimal totalPrice;
    private int reservedForHours;
    private LocalDate reservedAt;
}

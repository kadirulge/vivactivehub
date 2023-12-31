package com.ulgekadir.reservationservice.dtos.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class UpdateReservationRequest {
    @NotNull
    private UUID facilityId;
    @Min(1)
    private BigDecimal hourlyRate;
    @Min(1)
    private int reservedForHours;
    @NotNull
    private LocalDate reservedAt;
}

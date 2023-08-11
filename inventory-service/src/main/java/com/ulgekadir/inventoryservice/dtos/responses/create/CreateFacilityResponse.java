package com.ulgekadir.inventoryservice.dtos.responses.create;

import com.ulgekadir.inventoryservice.entities.enums.State;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFacilityResponse {
    private UUID id;
    private UUID categoryId;
    private UUID institutionId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private double hourlyRate;
    private String description;
    private State state;
}

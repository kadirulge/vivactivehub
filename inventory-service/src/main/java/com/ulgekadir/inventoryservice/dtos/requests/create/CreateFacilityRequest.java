package com.ulgekadir.inventoryservice.dtos.requests.create;

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
public class CreateFacilityRequest {
    @NotNull
    private UUID categoryId;
    @NotNull
    private UUID institutionId;
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;
    @NotBlank
    @Size(min = 2, max = 20)
    // TODO: add phone number regex
    private String phone;
    @Email
    private String email;
    @NotBlank
    @Size(min = 2, max = 50)
    private String address;
    @Min(0)
    private double hourlyRate;
    @NotBlank
    @Size(min = 2, max = 100)
    private String description;
    @NotNull
    private State state;

}

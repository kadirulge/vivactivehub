package com.ulgekadir.commonpackage.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityCreatedEvent implements Event{
    private UUID facilityId;
    private UUID institutionId;
    private UUID categoryId;
    private String institutionName;
    private String categoryName;
    private String name;
    private String phone;
    private String email;
    private String address;
    private BigDecimal hourlyRate;
    private String description;
    private String state;
}

package com.ulgekadir.filterservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Filter {
    @Id
    private String id;
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

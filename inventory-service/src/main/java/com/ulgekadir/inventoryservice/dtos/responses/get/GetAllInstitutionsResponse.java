package com.ulgekadir.inventoryservice.dtos.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInstitutionsResponse {
    private UUID id;
    private String name;
    private String phone;
    private String email;
}

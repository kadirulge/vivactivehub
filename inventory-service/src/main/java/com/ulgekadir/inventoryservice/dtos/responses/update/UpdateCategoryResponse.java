package com.ulgekadir.inventoryservice.dtos.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryResponse {
    private UUID id;
    private String name;
    private String description;
}

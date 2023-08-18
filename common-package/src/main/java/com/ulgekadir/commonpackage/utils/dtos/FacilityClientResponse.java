package com.ulgekadir.commonpackage.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityClientResponse extends ClientResponse {
    private String facilityName;
    private String categoryName;
    private String institutionName;
}

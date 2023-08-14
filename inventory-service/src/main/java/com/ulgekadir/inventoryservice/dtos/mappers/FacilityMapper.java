package com.ulgekadir.inventoryservice.dtos.mappers;

import com.ulgekadir.inventoryservice.dtos.requests.create.CreateFacilityRequest;
import com.ulgekadir.inventoryservice.dtos.requests.update.UpdateFacilityRequest;
import com.ulgekadir.inventoryservice.dtos.responses.create.CreateFacilityResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetAllFacilitiesResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetFacilityResponse;
import com.ulgekadir.inventoryservice.dtos.responses.update.UpdateFacilityResponse;
import com.ulgekadir.inventoryservice.entities.Facility;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FacilityMapper {
    FacilityMapper INSTANCE = Mappers.getMapper( FacilityMapper.class );
    Facility createFacilityRequestToFacility(CreateFacilityRequest createFacilityRequest);
    CreateFacilityResponse facilityToCreateFacilityResponse(Facility facility);
    Facility updateFacilityRequestToFacility(UpdateFacilityRequest updateFacilityRequest);
    UpdateFacilityResponse facilityToUpdateFacilityResponse(Facility facility);
    GetFacilityResponse facilityToGetFacilityResponse(Facility facility);
    GetAllFacilitiesResponse facilityToGetAllFacilitiesResponse(Facility facility);
}

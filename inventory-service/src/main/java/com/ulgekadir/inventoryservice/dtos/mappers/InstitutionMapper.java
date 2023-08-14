package com.ulgekadir.inventoryservice.dtos.mappers;

import com.ulgekadir.inventoryservice.dtos.requests.create.CreateInstitutionRequest;
import com.ulgekadir.inventoryservice.dtos.requests.update.UpdateInstitutionRequest;
import com.ulgekadir.inventoryservice.dtos.responses.create.CreateInstitutionResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetAllInstitutionsResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetInstitutionResponse;
import com.ulgekadir.inventoryservice.dtos.responses.update.UpdateInstitutionResponse;
import com.ulgekadir.inventoryservice.entities.Institution;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InstitutionMapper {
    InstitutionMapper INSTANCE = Mappers.getMapper( InstitutionMapper.class );
    Institution createInstitutionRequestToInstitution(CreateInstitutionRequest createInstitutionRequest);
    CreateInstitutionResponse institutionToCreateInstitutionResponse(Institution institution);
    Institution updateInstitutionRequestToInstitution(UpdateInstitutionRequest updateInstitutionRequest);
    UpdateInstitutionResponse institutionToUpdateInstitutionResponse(Institution institution);
    GetInstitutionResponse institutionToGetInstitutionResponse(Institution institution);
    GetAllInstitutionsResponse institutionToGetAllInstitutionsResponse(Institution institution);
}

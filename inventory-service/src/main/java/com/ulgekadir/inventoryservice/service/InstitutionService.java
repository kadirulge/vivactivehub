package com.ulgekadir.inventoryservice.service;

import com.ulgekadir.commonpackage.utils.mappers.ModelMapperService;
import com.ulgekadir.inventoryservice.dtos.requests.create.CreateInstitutionRequest;
import com.ulgekadir.inventoryservice.dtos.requests.update.UpdateInstitutionRequest;
import com.ulgekadir.inventoryservice.dtos.responses.create.CreateInstitutionResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetAllInstitutionsResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetInstitutionResponse;
import com.ulgekadir.inventoryservice.dtos.responses.update.UpdateInstitutionResponse;
import com.ulgekadir.inventoryservice.entities.Institution;
import com.ulgekadir.inventoryservice.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstitutionService {
    private final InstitutionRepository repository;
    private final ModelMapperService mapper;

    public List<GetAllInstitutionsResponse> getAll() {
        List<Institution>institutions = repository.findAll();
        List<GetAllInstitutionsResponse> response = institutions
                .stream()
                .map(institution -> mapper.forResponse().map(institution, GetAllInstitutionsResponse.class))
                .toList();
        return response;
    }

    public GetInstitutionResponse getById(UUID id) {
        Institution institution = repository.findById(id).orElseThrow();
        GetInstitutionResponse response = mapper.forResponse().map(institution, GetInstitutionResponse.class);
        return response;
    }

    public CreateInstitutionResponse add(CreateInstitutionRequest request) {
        Institution institution = mapper.forRequest().map(request, Institution.class);
        institution.setId(UUID.randomUUID());
        Institution createdInstitution = repository.save(institution);
        CreateInstitutionResponse response = mapper.forResponse().map(createdInstitution, CreateInstitutionResponse.class);
        return response;
    }

    public UpdateInstitutionResponse update(UUID id, UpdateInstitutionRequest request) {
        Institution institution = mapper.forRequest().map(request, Institution.class);
        institution.setId(id);
        repository.save(institution);
        UpdateInstitutionResponse response = mapper.forResponse().map(institution, UpdateInstitutionResponse.class);
        return response;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}

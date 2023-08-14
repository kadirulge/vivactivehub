package com.ulgekadir.inventoryservice.controllers;

import com.ulgekadir.inventoryservice.dtos.requests.create.CreateInstitutionRequest;
import com.ulgekadir.inventoryservice.dtos.requests.update.UpdateInstitutionRequest;
import com.ulgekadir.inventoryservice.dtos.responses.create.CreateInstitutionResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetAllInstitutionsResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetInstitutionResponse;
import com.ulgekadir.inventoryservice.dtos.responses.update.UpdateInstitutionResponse;
import com.ulgekadir.inventoryservice.service.InstitutionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/institutions")
public class InstitutionsController {
    private final InstitutionService service;

    @GetMapping
    public List<GetAllInstitutionsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetInstitutionResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateInstitutionResponse add(@Valid @RequestBody CreateInstitutionRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateInstitutionResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateInstitutionRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}

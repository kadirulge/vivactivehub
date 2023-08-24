package com.ulgekadir.inventoryservice.controllers;

import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import com.ulgekadir.inventoryservice.dtos.requests.create.CreateFacilityRequest;
import com.ulgekadir.inventoryservice.dtos.requests.update.UpdateFacilityRequest;
import com.ulgekadir.inventoryservice.dtos.responses.create.CreateFacilityResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetAllFacilitiesResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetFacilityResponse;
import com.ulgekadir.inventoryservice.dtos.responses.update.UpdateFacilityResponse;
import com.ulgekadir.inventoryservice.service.FacilityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/facilities")
public class FacilitiesController {
    private final FacilityService service;

    @GetMapping
    public List<GetAllFacilitiesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFacilityResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateFacilityResponse add(@Valid @RequestBody CreateFacilityRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateFacilityResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateFacilityRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PutMapping("/check-facility-availability-then-reserve/{id}")
    public ClientResponse checkIfFacilityAvailableThenReserve(@PathVariable UUID id) {
        return service.checkIfFacilityAvailableThenReserve(id);
    }

    @PutMapping("/change-state-to-available/{id}")
    public ClientResponse changeStateToAvailable(@PathVariable UUID id) {
        return service.changeStateToAvailable(id);
    }


}

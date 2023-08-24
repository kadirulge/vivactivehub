package com.ulgekadir.filterservice.controllers;

import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import com.ulgekadir.filterservice.dtos.GetAllFiltersResponse;
import com.ulgekadir.filterservice.dtos.GetFilterResponse;
import com.ulgekadir.filterservice.entities.Filter;
import com.ulgekadir.filterservice.service.FilterService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/filters")
public class FiltersController {
    private final FilterService service;

//    @PostConstruct // need to call this part for a single time to create db
//    public void createDb() {
//        System.err.println("PostConstruct");
//        service.add(new Filter());
//    }

    @GetMapping
    public List<GetAllFiltersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetFilterResponse getByIId(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/change-state-to-reserved/{facilityId}")
    public ClientResponse changeStateToReserved(@PathVariable UUID facilityId) {
       return service.changeStateToReserved(facilityId);
    }

    @PutMapping("/change-state-to-available/{facilityId}")
    public ClientResponse changeStateToAvailable(@PathVariable UUID facilityId) {
       return service.changeStateToAvailable(facilityId);
    }
}

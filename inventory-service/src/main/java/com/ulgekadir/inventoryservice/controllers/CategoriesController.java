package com.ulgekadir.inventoryservice.controllers;

import com.ulgekadir.inventoryservice.dtos.requests.create.CreateCategoryRequest;
import com.ulgekadir.inventoryservice.dtos.requests.update.UpdateCategoryRequest;
import com.ulgekadir.inventoryservice.dtos.responses.create.CreateCategoryResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetAllCategoriesResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetCategoryResponse;
import com.ulgekadir.inventoryservice.dtos.responses.update.UpdateCategoryResponse;
import com.ulgekadir.inventoryservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryService service;

    @GetMapping
    public List<GetAllCategoriesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetCategoryResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCategoryResponse add(@Valid @RequestBody CreateCategoryRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCategoryResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCategoryRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}

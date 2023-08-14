package com.ulgekadir.inventoryservice.service;

import com.ulgekadir.commonpackage.utils.mappers.ModelMapperService;
import com.ulgekadir.inventoryservice.dtos.requests.create.CreateCategoryRequest;
import com.ulgekadir.inventoryservice.dtos.requests.update.UpdateCategoryRequest;
import com.ulgekadir.inventoryservice.dtos.responses.create.CreateCategoryResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetAllCategoriesResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetCategoryResponse;
import com.ulgekadir.inventoryservice.dtos.responses.update.UpdateCategoryResponse;
import com.ulgekadir.inventoryservice.entities.Category;
import com.ulgekadir.inventoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final ModelMapperService mapper;

    public List<GetAllCategoriesResponse> getAll() {
        List<Category>categories = repository.findAll();
        List<GetAllCategoriesResponse> response = categories
                .stream()
                .map(category -> mapper.forResponse().map(category, GetAllCategoriesResponse.class))
                .toList();
        return response;
    }

    public GetCategoryResponse getById(UUID id) {
        Category category = repository.findById(id).orElseThrow();
        GetCategoryResponse response = mapper.forResponse().map(category, GetCategoryResponse.class);
        return response;
    }

    public CreateCategoryResponse add(CreateCategoryRequest request) {
        Category category = mapper.forRequest().map(request, Category.class);
        category.setId(UUID.randomUUID());
        Category createdCategory = repository.save(category);
        CreateCategoryResponse response = mapper.forResponse().map(createdCategory, CreateCategoryResponse.class);
        return response;
    }

    public UpdateCategoryResponse update(UUID id, UpdateCategoryRequest request) {
        Category category = mapper.forRequest().map(request, Category.class);
        category.setId(id);
        repository.save(category);
        UpdateCategoryResponse response = mapper.forResponse().map(category, UpdateCategoryResponse.class);
        return response;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}

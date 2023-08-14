package com.ulgekadir.inventoryservice.dtos.mappers;

import com.ulgekadir.inventoryservice.dtos.requests.create.CreateCategoryRequest;
import com.ulgekadir.inventoryservice.dtos.requests.update.UpdateCategoryRequest;
import com.ulgekadir.inventoryservice.dtos.responses.create.CreateCategoryResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetAllCategoriesResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetCategoryResponse;
import com.ulgekadir.inventoryservice.dtos.responses.update.UpdateCategoryResponse;
import com.ulgekadir.inventoryservice.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper( CategoryMapper.class );
    Category createCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);
    CreateCategoryResponse categoryToCreateCategoryResponse(Category category);
    Category updateCategoryRequestToCategory(UpdateCategoryRequest updateCategoryRequest);
    UpdateCategoryResponse categoryToUpdateCategoryResponse(Category category);
    GetCategoryResponse categoryToGetCategoryResponse(Category category);
    GetAllCategoriesResponse categoryToGetAllCategoriesResponse(Category category);
}

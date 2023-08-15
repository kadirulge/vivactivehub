package com.ulgekadir.filterservice.service;

import com.ulgekadir.commonpackage.utils.mappers.ModelMapperService;
import com.ulgekadir.filterservice.dtos.GetAllFiltersResponse;
import com.ulgekadir.filterservice.dtos.GetFilterResponse;
import com.ulgekadir.filterservice.entities.Filter;
import com.ulgekadir.filterservice.repository.FilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilterService {
    private final FilterRepository repository;
    private final ModelMapperService mapper;

    public List<GetAllFiltersResponse> getAll(){
        List<Filter> filters = repository.findAll();
        List<GetAllFiltersResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();
        return response;
    }

    public GetFilterResponse getById(UUID id){
        Filter filter = repository.findById(id).orElseThrow();
        GetFilterResponse response = mapper.forResponse().map(filter, GetFilterResponse.class);
        return response;
    }

    public void add(Filter filter){
        repository.save(filter);
    }

    public void delete(UUID id){
        repository.deleteById(id);
    }

}

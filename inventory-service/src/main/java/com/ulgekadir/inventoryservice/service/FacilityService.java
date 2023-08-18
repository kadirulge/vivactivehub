package com.ulgekadir.inventoryservice.service;

import com.ulgekadir.commonpackage.events.FacilityCreatedEvent;
import com.ulgekadir.commonpackage.utils.kafka.KafkaProducer;
import com.ulgekadir.commonpackage.utils.mappers.ModelMapperService;
import com.ulgekadir.inventoryservice.dtos.requests.create.CreateFacilityRequest;
import com.ulgekadir.inventoryservice.dtos.requests.update.UpdateFacilityRequest;
import com.ulgekadir.inventoryservice.dtos.responses.create.CreateFacilityResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetAllFacilitiesResponse;
import com.ulgekadir.inventoryservice.dtos.responses.get.GetFacilityResponse;
import com.ulgekadir.inventoryservice.dtos.responses.update.UpdateFacilityResponse;
import com.ulgekadir.inventoryservice.entities.Facility;
import com.ulgekadir.inventoryservice.entities.enums.State;
import com.ulgekadir.inventoryservice.repository.FacilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FacilityService {
    private final FacilityRepository repository;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;


    public List<GetAllFacilitiesResponse> getAll() {
        List<Facility>facilities = repository.findAll();
        List<GetAllFacilitiesResponse> response = facilities
                .stream()
                .map(facility -> mapper.forResponse().map(facility, GetAllFacilitiesResponse.class))
                .toList();
        return response;
    }

    public GetFacilityResponse getById(UUID id) {
        Facility facility = repository.findById(id).orElseThrow();
        GetFacilityResponse response = mapper.forResponse().map(facility, GetFacilityResponse.class);
        return response;
    }

    public CreateFacilityResponse add(CreateFacilityRequest request) {
        Facility facility = mapper.forRequest().map(request, Facility.class);
        facility.setId(UUID.randomUUID());
        facility.setState(State.AVAILABLE);
        Facility createdFacility = repository.save(facility);
        sendKafkaFacilityCreatedEvent(createdFacility);
        CreateFacilityResponse response = mapper.forResponse().map(createdFacility, CreateFacilityResponse.class);
        return response;
    }

    public UpdateFacilityResponse update(UUID id, UpdateFacilityRequest request) {
        Facility facility = mapper.forRequest().map(request, Facility.class);
        facility.setId(id);
        repository.save(facility);
        UpdateFacilityResponse response = mapper.forResponse().map(facility, UpdateFacilityResponse.class);
        return response;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private void sendKafkaFacilityCreatedEvent(Facility createdFacility) {
        FacilityCreatedEvent event = mapper.forResponse().map(createdFacility, FacilityCreatedEvent.class);
        producer.sendMessage(event, "facility-created");
    }

}

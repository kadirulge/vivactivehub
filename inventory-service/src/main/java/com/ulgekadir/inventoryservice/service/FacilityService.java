package com.ulgekadir.inventoryservice.service;

import com.ulgekadir.commonpackage.events.FacilityCreatedEvent;
import com.ulgekadir.commonpackage.events.FacilityDeletedEvent;
import com.ulgekadir.commonpackage.events.FacilityUpdatedEvent;
import com.ulgekadir.commonpackage.exceptions.BusinessException;
import com.ulgekadir.commonpackage.utils.constants.Messages;
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
import com.ulgekadir.inventoryservice.service.rules.FacilityBusinessRules;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FacilityService {
    private final FacilityRepository repository;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;
    private final FacilityBusinessRules rules;

    public List<GetAllFacilitiesResponse> getAll() {
        List<Facility>facilities = repository.findAll();
        List<GetAllFacilitiesResponse> response = facilities
                .stream()
                .map(facility -> mapper.forResponse().map(facility, GetAllFacilitiesResponse.class))
                .toList();
        return response;
    }

    public GetFacilityResponse getById(UUID id) {
        Facility facility = repository.findById(id).orElseThrow(()->new BusinessException(Messages.Facility.NotExists));
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
        rules.checkIfFacilityExists(id);
        Facility facility = mapper.forRequest().map(request, Facility.class);
        facility.setId(id);
        Facility updatedFacility =repository.save(facility);
        sendKafkaFacilityUpdatedEvent(updatedFacility);
        UpdateFacilityResponse response = mapper.forResponse().map(updatedFacility, UpdateFacilityResponse.class);
        return response;
    }

    public void delete(UUID id) {
        rules.checkIfFacilityExists(id);
        repository.deleteById(id);
        sendKafkaFacilityDeletedEvent(id);
    }

    private void sendKafkaFacilityCreatedEvent(Facility createdFacility) {
        FacilityCreatedEvent event = mapper.forResponse().map(createdFacility, FacilityCreatedEvent.class);
        producer.sendMessage(event, "facility-created");
    }

    private void sendKafkaFacilityUpdatedEvent(Facility updatedFacility) {
        FacilityUpdatedEvent event = mapper.forResponse().map(updatedFacility, FacilityUpdatedEvent.class);
        producer.sendMessage(event, "facility-updated");
    }

    private void sendKafkaFacilityDeletedEvent(UUID id) {
        producer.sendMessage(new FacilityDeletedEvent(id), "facility-deleted");
    }

}

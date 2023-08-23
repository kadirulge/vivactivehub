package com.ulgekadir.inventoryservice.service.rules;

import com.ulgekadir.commonpackage.exceptions.BusinessException;
import com.ulgekadir.commonpackage.utils.constants.Messages;
import com.ulgekadir.inventoryservice.entities.Facility;
import com.ulgekadir.inventoryservice.entities.enums.State;
import com.ulgekadir.inventoryservice.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FacilityBusinessRules {
    private final FacilityRepository repository;

    public void checkIfFacilityExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Facility.NotExists);
        }
    }

    public void checkFacilityAvailability(UUID id) {
        Facility facility = repository.findById(id).orElseThrow();
        if (!facility.getState().equals(State.AVAILABLE)) {
            throw new BusinessException(Messages.Facility.NotAvailable);
        }
    }
}

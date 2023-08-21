package com.ulgekadir.inventoryservice.service.rules;

import com.ulgekadir.commonpackage.exceptions.BusinessException;
import com.ulgekadir.commonpackage.utils.constants.Messages;
import com.ulgekadir.inventoryservice.repository.InstitutionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstitutionBusinessRules {
    private final InstitutionRepository repository;

    public void checkIfInstitutionExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Institution.NotExists);
        }
    }


}

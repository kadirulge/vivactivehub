package com.ulgekadir.inventoryservice.service.rules;

import com.ulgekadir.commonpackage.exceptions.BusinessException;
import com.ulgekadir.commonpackage.utils.constants.Messages;
import com.ulgekadir.inventoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryBusinessRules {
    private final CategoryRepository repository;

    public void checkIfCategoryExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Category.NotExists);
        }
    }

}

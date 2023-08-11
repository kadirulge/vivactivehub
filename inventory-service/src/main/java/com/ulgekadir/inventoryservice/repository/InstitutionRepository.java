package com.ulgekadir.inventoryservice.repository;

import com.ulgekadir.inventoryservice.entities.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstitutionRepository extends JpaRepository<Institution, UUID> {
}

package com.ulgekadir.inventoryservice.repository;

import com.ulgekadir.inventoryservice.entities.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FacilityRepository extends JpaRepository<Facility, UUID> {
}

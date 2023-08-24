package com.ulgekadir.filterservice.repository;

import com.ulgekadir.filterservice.entities.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FilterRepository extends MongoRepository<Filter, UUID> {
    void deleteByFacilityId(UUID facilityId);
    void deleteAllByInstitutionId(UUID institutionId);
    Filter findByFacilityId(UUID facilityId);
}

package com.ulgekadir.reservationservice.clients;

import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@Retry(name = "retryToFilterService")
@FeignClient(name = "filter-service", fallback = FilterClientFallback.class)
public interface FilterClient {
    @PutMapping(value = "/api/filters/change-state-to-reserved/{facilityId}")
    ClientResponse changeStateToReserved(@PathVariable UUID facilityId) throws InterruptedException;

    @PutMapping(value = "/api/filters/change-state-to-available/{facilityId}")
    ClientResponse changeStateToAvailable(@PathVariable UUID facilityId) throws InterruptedException;
}

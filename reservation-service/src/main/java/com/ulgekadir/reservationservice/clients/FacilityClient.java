package com.ulgekadir.reservationservice.clients;

import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@Retry(name = "retryToInventoryService")
@FeignClient(name = "inventory-service", fallback = FacilityClientFallback.class)
public interface FacilityClient {
    @GetMapping(value = "/api/facilities/check-facility-availability-then-reserve/{facilityId}")
    ClientResponse checkIfFacilityAvailableAndReserve(@PathVariable UUID facilityId) throws InterruptedException;


}
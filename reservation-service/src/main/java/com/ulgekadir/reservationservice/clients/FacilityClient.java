package com.ulgekadir.reservationservice.clients;

import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import com.ulgekadir.commonpackage.utils.dtos.FacilityClientResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@Retry(name = "retryToInventoryService")
@FeignClient(name = "inventory-service", fallback = FacilityClientFallback.class)
public interface FacilityClient {
    @PutMapping(value = "/api/facilities/check-facility-availability-then-reserve/{facilityId}")
    ClientResponse checkIfFacilityAvailableAndReserve(@PathVariable UUID facilityId) throws InterruptedException;

    @PutMapping(value = "/api/facilities/change-state-to-available/{facilityId}")
    ClientResponse changeStateToAvailable(@PathVariable UUID facilityId) throws InterruptedException;

    @GetMapping(value = "/api/facilities/get-facility-for-invoice/{facilityId}")
    FacilityClientResponse getFacilityForInvoice(@PathVariable UUID facilityId) throws InterruptedException;


}
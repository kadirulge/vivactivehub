package com.ulgekadir.reservationservice.clients;

import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import com.ulgekadir.commonpackage.utils.dtos.FacilityClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component // This annotation is necessary for the fallback class to be found by Spring
public class FacilityClientFallback implements FacilityClient {

    @Override
    public ClientResponse checkIfFacilityAvailableAndReserve(UUID facilityId) throws InterruptedException {
        log.info("INVENTORY SERVICE IS DOWN! CheckIfFacilityAvailableAndReserve");
        throw new RuntimeException("INVENTORY SERVICE IS DOWN!");
    }

    @Override
    public ClientResponse changeStateToAvailable(UUID facilityId) throws InterruptedException {
        log.info("INVENTORY SERVICE IS DOWN! ChangeStateToAvailable");
        throw new RuntimeException("INVENTORY SERVICE IS DOWN!");
    }

    @Override
    public FacilityClientResponse getFacilityForInvoice(UUID carId) throws InterruptedException {
        log.info("INVENTORY SERVICE IS DOWN! GetFacilityForInvoice");
        throw new RuntimeException("INVENTORY SERVICE IS DOWN!");
    }
}

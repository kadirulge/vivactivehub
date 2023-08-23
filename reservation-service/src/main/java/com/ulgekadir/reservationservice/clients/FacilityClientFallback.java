package com.ulgekadir.reservationservice.clients;

import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component // This annotation is necessary for the fallback class to be found by Spring
public class FacilityClientFallback implements FacilityClient {
    @Override
    public ClientResponse checkIfFacilityAvailable(UUID carId) {
        log.info("INVENTORY SERVICE IS DOWN! CheckIfFacilityAvailable");
        throw new RuntimeException("INVENTORY SERVICE IS DOWN!");
    }

}

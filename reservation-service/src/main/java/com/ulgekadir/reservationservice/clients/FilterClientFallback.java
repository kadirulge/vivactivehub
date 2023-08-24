package com.ulgekadir.reservationservice.clients;

import com.ulgekadir.commonpackage.utils.dtos.ClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class FilterClientFallback implements FilterClient {
    @Override
    public ClientResponse changeStateToReserved(UUID facilityId) throws InterruptedException {
        log.info("FILTER SERVICE IS DOWN! ChangeStateToReserved");
        throw new RuntimeException("FILTER SERVICE IS DOWN!");
    }
}

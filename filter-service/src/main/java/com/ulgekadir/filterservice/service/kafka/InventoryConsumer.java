package com.ulgekadir.filterservice.service.kafka;

import com.ulgekadir.commonpackage.events.FacilityCreatedEvent;
import com.ulgekadir.commonpackage.utils.mappers.ModelMapperService;
import com.ulgekadir.filterservice.entities.Filter;
import com.ulgekadir.filterservice.service.FilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryConsumer {
    private final FilterService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "facility-created",
            groupId = "facility-create"
    )
    public void consume(FacilityCreatedEvent event){
        var filter = mapper.forRequest().map(event, Filter.class);
        service.add(filter);
        log.info("Facility created event consumed {} ",event);
    }

}

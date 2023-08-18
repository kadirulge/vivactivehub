package com.ulgekadir.commonpackage.utils.mappers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;


public class ModelMapperService {

    private final ModelMapper mapper;

    public ModelMapperService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ModelMapper forResponse() {
        mapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        return mapper;
    }


    public ModelMapper forRequest() {
        mapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        return mapper;
    }
}

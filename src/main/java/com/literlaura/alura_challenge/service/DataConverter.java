package com.literlaura.alura_challenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class DataConverter implements DataConverterImpl{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertData(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json,tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

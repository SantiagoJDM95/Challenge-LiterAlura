package com.literalura_Challenge.Challenge.LiterAlura.Services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorData implements Conversor{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getData ( String json, Class<T> tClass ) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

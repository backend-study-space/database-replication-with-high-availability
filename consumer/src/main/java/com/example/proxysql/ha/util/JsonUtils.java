package com.example.proxysql.ha.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T convertObjectToDto(Object obj, Class<T> dtoClass) {
        return objectMapper.convertValue(obj, dtoClass);
    }

    public static <T> T convertObjectToDto(Object obj, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(obj), typeReference);
        } catch (IOException e) {
            throw new RuntimeException("JSON parsing error", e);
        }
    }
}

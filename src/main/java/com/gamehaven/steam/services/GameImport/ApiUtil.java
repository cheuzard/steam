package com.gamehaven.steam.services.GameImport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamehaven.steam.exceptions.ExternalApiException;
import org.springframework.http.ResponseEntity;

public class ApiUtil {
    static JsonNode parseBody(ResponseEntity<String> rsp, String context) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(rsp.getBody());
        } catch (JsonProcessingException e) {
            throw new ExternalApiException(context + ": malformed JSON", e);
        }
    }
}

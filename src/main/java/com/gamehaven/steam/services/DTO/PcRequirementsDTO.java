package com.gamehaven.steam.services.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import java.io.IOException;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = PcRequirementsDeserializer.class)
public class PcRequirementsDTO {
    private String minimum;
    private String recommended;
}

/**
 * Custom deserializer to handle both array and object formats for pc_requirements
 */
class PcRequirementsDeserializer extends JsonDeserializer<PcRequirementsDTO> {

    @Override
    public PcRequirementsDTO deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode node = p.getCodec().readTree(p);
        PcRequirementsDTO requirements = new PcRequirementsDTO();

        // Handle the case when pc_requirements is an array
        if (node.isArray()) {
            // Just return an empty DTO - we can't reliably parse array format
            return requirements;
        }

        // Handle the case when pc_requirements is an object
        if (node.isObject()) {
            if (node.has("minimum")) {
                requirements.setMinimum(node.get("minimum").asText());
            }

            if (node.has("recommended")) {
                requirements.setRecommended(node.get("recommended").asText());
            }
        }

        return requirements;
    }
}
package com.labmedical.backend.dtos.annotations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.labmedical.backend.entities.Patient;

import java.io.IOException;

public class MaritalStatusDeserializer extends JsonDeserializer<Patient.MaritalStatus> {
    @Override
    public Patient.MaritalStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String maritalStatus = node.asText();
        try {
            return Patient.MaritalStatus.valueOf(maritalStatus);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid marital status: " + maritalStatus);
        }
    }
}


package com.labmedical.backend.dtos.annotations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.labmedical.backend.entities.UsersType;

import java.io.IOException;

public class UserTypeDeserializer extends JsonDeserializer<UsersType> {
    @Override
    public UsersType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String userType = node.asText();
        try {
            return UsersType.valueOf(userType);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid user type: " + userType + "please enter: DOCTOR, NURSE, ADMINISTRATOR");
        }
    }
}


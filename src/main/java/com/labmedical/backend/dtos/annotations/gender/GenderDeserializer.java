package com.labmedical.backend.dtos.annotations.gender;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.labmedical.backend.entities.Person;

import java.io.IOException;

public class GenderDeserializer extends JsonDeserializer<Person.Gender> {
    @Override
    public Person.Gender deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String gender = node.asText();
        try {
            return Person.Gender.valueOf(gender);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid gender: " + gender + ". It need to be: " +
                    "\"MALE\", \"FEMALE\" or \"OTHER\"");
        }
    }
}


package com.util.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class StringToInstantDeserializer extends JsonDeserializer<Instant> {
    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
        String s = jsonParser.getText();
        if (s != null && !s.isEmpty()) {
            LocalDate localDate = LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
        }
        return null;
    }
}
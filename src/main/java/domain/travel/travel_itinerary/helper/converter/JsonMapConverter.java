package domain.travel.travel_itinerary.helper.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.travel.travel_itinerary.exception.IllegalArgumentException;
import io.jsonwebtoken.lang.Maps;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Converter
public class JsonMapConverter implements AttributeConverter<Map<String, Objects>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Objects> attributes) {
        try {
            return attributes == null ? null : objectMapper.writeValueAsString(attributes);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert Maps to JSON", e);
        }
    }

    @Override
    public Map<String, Objects> convertToEntityAttribute(String dbData) {
        try {
            return dbData == null ? null : objectMapper.readValue(dbData, new TypeReference<>() {});
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot convert Maps to JSON", e);
        }
    }
}

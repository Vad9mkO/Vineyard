package com.vineyard.courseproject.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vineyard.courseproject.domain.Environment;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EnvironmentJsonSerializer extends StdSerializer<Environment>{

    public EnvironmentJsonSerializer() {
        this(null);
    }

    public EnvironmentJsonSerializer(Class<Environment> env) {
        super(env);
    }

    @Override
    public void serialize(Environment env, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", env.getId());
        jsonGenerator.writeNumberField("temperature", env.getTemperature());
        jsonGenerator.writeNumberField("luminousity", env.getLuminousity());
        jsonGenerator.writeNumberField("airHumidity", env.getAirHumidity());
        jsonGenerator.writeNumberField("soidHumidity", env.getSoilHumidity());
        jsonGenerator.writeBooleanField("verminStatus", env.getVerminStatus());
        jsonGenerator.writeNumberField("vineyard", env.getBush().getVineyard().getId());
        jsonGenerator.writeEndObject();
    }
}

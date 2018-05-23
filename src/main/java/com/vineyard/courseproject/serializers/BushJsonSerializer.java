package com.vineyard.courseproject.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vineyard.courseproject.domain.Bush;
import com.vineyard.courseproject.domain.Environment;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class BushJsonSerializer extends StdSerializer<Bush> {

    public BushJsonSerializer () {
        this(null);
    }

    public BushJsonSerializer (Class<Bush> env) {
        super(env);
    }

    @Override
    public void serialize(Bush bush, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", bush.getId());
        jsonGenerator.writeNumberField("height", bush.getHeight());
        jsonGenerator.writeNumberField("grapesWeight", bush.getGrapesWeight());
        jsonGenerator.writeBooleanField("healthStatus", bush.getHealthStatus());
        jsonGenerator.writeNumberField("vineyard", bush.getVineyard().getId());

        jsonGenerator.writeEndObject();
    }
}



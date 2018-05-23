package com.vineyard.courseproject.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vineyard.courseproject.domain.Environment;
import com.vineyard.courseproject.domain.Vineyard;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class VineyardJsonSerializer extends StdSerializer<Vineyard> {

    public VineyardJsonSerializer() {
        this(null);
    }

    public VineyardJsonSerializer(Class<Vineyard> env) {
        super(env);
    }

    @Override
    public void serialize(Vineyard vine, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", vine.getId());
        jsonGenerator.writeStringField("country", vine.getCountry());
        jsonGenerator.writeStringField("region", vine.getRegion());
        jsonGenerator.writeNumberField("square", vine.getSquare());
        jsonGenerator.writeStringField("soilType", vine.getSoilType());
        jsonGenerator.writeNumberField("client", vine.getClient().getId());

        jsonGenerator.writeEndObject();
    }
}


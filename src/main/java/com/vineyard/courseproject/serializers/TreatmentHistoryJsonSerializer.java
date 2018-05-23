package com.vineyard.courseproject.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vineyard.courseproject.domain.TreatmentHistory;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.SimpleDateFormat;

@JsonComponent
public class TreatmentHistoryJsonSerializer extends StdSerializer<TreatmentHistory> {

    public TreatmentHistoryJsonSerializer () {
        this(null);
    }

    public TreatmentHistoryJsonSerializer (Class<TreatmentHistory> trHis) {
        super(trHis);
    }

    @Override
    public void serialize(TreatmentHistory trHis, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", trHis.getId());
        jsonGenerator.writeStringField("date", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(trHis.getDate()));
        jsonGenerator.writeStringField("treatmentType", trHis.getTreatmentType());
        jsonGenerator.writeStringField("staff", trHis.getStaff());
        jsonGenerator.writeBooleanField("success", trHis.getSuccess());
        jsonGenerator.writeStringField("notes", trHis.getNotes());
        jsonGenerator.writeNumberField("bushId", trHis.getBush().getId());

        jsonGenerator.writeEndObject();
    }
}

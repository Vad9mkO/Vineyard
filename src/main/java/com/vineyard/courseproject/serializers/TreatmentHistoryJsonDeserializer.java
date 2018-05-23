package com.vineyard.courseproject.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.vineyard.courseproject.domain.Bush;
import com.vineyard.courseproject.domain.TreatmentHistory;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonComponent
public class TreatmentHistoryJsonDeserializer extends StdDeserializer<TreatmentHistory> {

    public TreatmentHistoryJsonDeserializer() {
        this(null);
    }

    public TreatmentHistoryJsonDeserializer (Class<TreatmentHistory> trHis) {
        super(trHis);
    }

    @Override
    public TreatmentHistory deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        TreatmentHistory treatmentHistory = new TreatmentHistory();

        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(((TextNode)treeNode.get("date")).asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Bush bush = new Bush();

        bush.setId(((IntNode)treeNode.get("bushId")).intValue());
        treatmentHistory.setId(((IntNode)treeNode.get("id")).intValue());
        treatmentHistory.setDate(date);
        treatmentHistory.setTreatmentType(((TextNode)treeNode.get("treatmentType")).asText());
        treatmentHistory.setStaff(((TextNode)treeNode.get("staff")).asText());
        treatmentHistory.setSuccess(((BooleanNode)treeNode.get("success")).asBoolean());
        treatmentHistory.setNotes(((TextNode)treeNode.get("notes")).asText());
        treatmentHistory.setBush(bush);

        return treatmentHistory;
    }
}

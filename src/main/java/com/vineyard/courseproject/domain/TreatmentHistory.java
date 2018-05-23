package com.vineyard.courseproject.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vineyard.courseproject.serializers.TreatmentHistoryJsonDeserializer;
import com.vineyard.courseproject.serializers.TreatmentHistoryJsonSerializer;

import javax.persistence.*;
import java.util.Date;

/**
 *  Treatment types (fixed):
 *
 *      Irrigation
 *      Chemical treatment
 *      Fertilizing
 *
 *  Staff used (user defined):
 *
 *      Water
 *      CO3H2
 *      TNT
 *      Soil and so on ...
 */

@Entity
@JsonSerialize(using = TreatmentHistoryJsonSerializer.class)
@JsonDeserialize(using = TreatmentHistoryJsonDeserializer.class)
public class TreatmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "treatment_type")
    private String treatmentType;

    private String staff;

    private boolean success;

    private String notes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bush_id", nullable = false)
    private Bush bush;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Bush getBush() {
        return bush;
    }

    public void setBush(Bush bush) {
        this.bush = bush;
    }
}

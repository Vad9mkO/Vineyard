package com.vineyard.courseproject.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vineyard.courseproject.serializers.BushJsonSerializer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonSerialize(using = BushJsonSerializer.class)
public class Bush implements Serializable {

    private static final long serialVersionUID = 2259943519977343907L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "height")
    private double height;

    @Column(name = "grapes_weight")
    private double grapesWeight;

    @Column(name = "health_status")
    private boolean healthStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vineyard_id", nullable =  false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Vineyard vineyard;

    //Parent entity, save "bush" and linked "environment" hibernate saves automatically
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "bush")
    private Environment environment;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "bush")
    private TreatmentHistory treatmentHistory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getGrapesWeight() {
        return grapesWeight;
    }

    public void setGrapesWeight(double grapesWeight) {
        this.grapesWeight = grapesWeight;
    }

    public boolean getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(boolean healthStatus) {
        this.healthStatus = healthStatus;
    }

    public Vineyard getVineyard() {
        return vineyard;
    }

    public void setVineyard(Vineyard vineyard) {
        this.vineyard = vineyard;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public TreatmentHistory getTreatmentHistory() {
        return treatmentHistory;
    }

    public void setTreatmentHistory(TreatmentHistory treatmentHistory) {
        this.treatmentHistory = treatmentHistory;
    }

    @Override
    public String toString() {
        return "Bush{" +
                "id=" + id +
                ", height=" + height +
                ", grapesWeight=" + grapesWeight +
                ", healthStatus=" + healthStatus +
                ", vineyard=" + vineyard +
                ", environment=" + environment +
                '}';
    }
}

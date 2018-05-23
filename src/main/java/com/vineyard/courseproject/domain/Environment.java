package com.vineyard.courseproject.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vineyard.courseproject.serializers.EnvironmentJsonSerializer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonSerialize(using = EnvironmentJsonSerializer.class)
public class Environment implements Serializable {

    private static final long serialVersionUID = 2259943519977343907L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "luminousity")
    private double luminousity;

    @Column(name = "air_humidity")
    private double airHumidity;

    @Column(name = "soil_humidity")
    private double soilHumidity;

    @Column(name = "vermin_status")
    private boolean verminStatus;

    //Child entity, saved automatically when parent saved manually

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bush_id", nullable = false)
    private Bush bush;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getLuminousity() {
        return luminousity;
    }

    public void setLuminousity(double luminousity) {
        this.luminousity = luminousity;
    }

    public double getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(double airHumidity) {
        this.airHumidity = airHumidity;
    }

    public double getSoilHumidity() {
        return soilHumidity;
    }

    public void setSoilHumidity(double soilHumidity) {
        this.soilHumidity = soilHumidity;
    }

    public boolean getVerminStatus() {
        return verminStatus;
    }

    public void setVerminStatus(boolean verminStatus) {
        this.verminStatus = verminStatus;
    }

    public Bush getBush() {
        return bush;
    }

    public void setBush(Bush bush) {
        this.bush = bush;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", luminousity=" + luminousity +
                ", airHumidity=" + airHumidity +
                ", soilHumidity=" + soilHumidity +
                ", verminStatus=" + verminStatus +
                ", bush=" + bush +
                '}';
    }
}

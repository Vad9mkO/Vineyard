package com.vineyard.courseproject.domain;

import javax.persistence.*;

@Entity
public class Environment {

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

    public boolean isVerminStatus() {
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

    //Child entity, saved automatically when parent saved manually
    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "bush_id", nullable = false)
    private Bush bush;
}

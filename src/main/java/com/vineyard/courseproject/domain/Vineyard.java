package com.vineyard.courseproject.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vineyard.courseproject.serializers.VineyardJsonSerializer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonSerialize(using = VineyardJsonSerializer.class)
public class Vineyard implements Serializable {

    private static final long serialVersionUID = 2259943519977343907L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @Column(name = "square")
    private double square;

    @Column(name = "soil_type")
    private String soilType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Vineyard{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", square=" + square +
                ", soilType='" + soilType + '\'' +
                ", client=" + client +
                '}';
    }
}

package com.vineyard.courseproject.domain;

import javax.persistence.*;

@Entity
//@Table(name = "topic", catalog = "coursedatabase")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String text;

    public Topic() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

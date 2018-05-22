package com.vineyard.courseproject.domain;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserSession implements Serializable {

//    private static final long serialVersionUID = 1L;
    private static final long serialVersionUID = 2259943519977343907L;

    @NotEmpty
    private String uid;

    private String email;

    private int databaseId;

    private Map<String, String> attributes = new HashMap<>();

    public int getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(int databaseId) {
        this.databaseId = databaseId;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void putAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public UserSession() {}

    @Override
    public String toString() {
        return "UserSession{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", databaseId=" + databaseId +
                ", attributes=" + attributes +
                '}';
    }
}

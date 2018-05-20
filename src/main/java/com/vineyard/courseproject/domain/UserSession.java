package com.vineyard.courseproject.domain;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String uid;

    private Map<String, String> attributes = new HashMap<>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
                ", attributes=" + attributes +
                '}';
    }
}

package com.vineyard.courseproject;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class RedisVO implements Serializable {

    private static final long serialVersionUID = 2259943519977343907L;

    @Id
    private int keyID;

    private String dbname;
    private String dbversion;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getKeyID() {
        return keyID;
    }

    public void setKeyID(int keyID) {
        this.keyID = keyID;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbversion() {
        return dbversion;
    }

    public void setDbversion(String dbversion) {
        this.dbversion = dbversion;
    }

    @Override
    public String toString() {
        return "RedisVO{" +
                "keyID=" + keyID +
                ", dbname='" + dbname + '\'' +
                ", dbversion='" + dbversion + '\'' +
                '}';
    }
}

package org.DTOs;

import java.sql.Date;

public class Scout {
    private String id;
    private String scout_name;
    private Date DOB;

    public Scout(String scout_name, Date DOB) {
        this.scout_name = scout_name;
        this.DOB = DOB;
    }

    public Scout(String id, String scout_name, Date DOB) {
        this.id = id;
        this.scout_name = scout_name;
        this.DOB = DOB;
    }

    public Scout() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScout_name() {
        return scout_name;
    }

    public void setScout_name(String scout_name) {
        this.scout_name = scout_name;
    }

    public String getDOB() {
        return DOB.toString();
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "Scout{" +
                "id='" + id + '\'' +
                ", scout_name='" + scout_name + '\'' +
                ", DOB=" + DOB +
                '}';
    }
}

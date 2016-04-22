package com.delexa.chudobilet;

import java.util.Date;

public class Establishment {

    public Establishment(String type, String name, String address, Date timeStamp) {
        this.address = address;
        this.id = 0;
        this.name = name;
        this.timeStamp = timeStamp;
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private int id;
    private String type;
    private String name;
    private String address;
    private Date timeStamp;

}

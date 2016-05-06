package com.delexa.chudobilet.main;

import com.delexa.chudobilet.main.Event;

import java.util.Date;

public class Seat {

    private int id;
    private Event event;
    private String name;
    private Date timeDate;
    private double price;
    private double servicePrice;
    private int isFree;
    private Date timeStamp;

    public Seat(Event event, String name, Date timeDate, double price, double servicePrice, Date timeStamp) {
        this.id = 0;
        this.event = event;
        this.name = name;
        this.timeDate = timeDate;
        this.price = price;
        this.servicePrice = servicePrice;
        this.isFree=0;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(Date timeDate) {
        this.timeDate = timeDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

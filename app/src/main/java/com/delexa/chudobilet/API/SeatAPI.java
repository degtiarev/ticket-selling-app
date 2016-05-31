package com.delexa.chudobilet.API;

import java.util.Date;

public class SeatAPI {

    private String eventName;
    private String name;
    private String timeDate;
    private double price;
    private double servicePrice;
    private int isFree;
    private String timeStamp;

    public SeatAPI() {
    }

    public SeatAPI(double servicePrice, String eventName, String name, String timeDate, double price, int isFree, String timeStamp) {
        this.servicePrice = servicePrice;
        this.eventName = eventName;
        this.name = name;
        this.timeDate = timeDate;
        this.price = price;
        this.isFree = isFree;
        this.timeStamp = timeStamp;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}

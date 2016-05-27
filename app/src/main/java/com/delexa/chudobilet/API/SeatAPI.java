package com.delexa.chudobilet.API;

import java.util.Date;

public class SeatAPI {

    private int id;
    private EventAPI eventAPI;
    private String name;
    private Date timeDate;
    private double price;
    private double servicePrice;
    private int isFree;
    private Date timeStamp;

    public SeatAPI(EventAPI eventAPI, String name, Date timeDate, double price, double servicePrice, Date timeStamp) {
        this.id = 0;
        this.eventAPI = eventAPI;
        this.name = name;
        this.timeDate = timeDate;
        this.price = price;
        this.servicePrice = servicePrice;
        this.isFree=0;
        this.timeStamp = timeStamp;
    }

    public SeatAPI()
    {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventAPI getEventAPI() {
        return eventAPI;
    }

    public void setEventAPI(EventAPI eventAPI) {
        this.eventAPI = eventAPI;
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

package com.delexa.chudobilet.DBClasses;


import java.util.Date;

public class Subscription {

    private int id;
    private Event event;
    private int amountSeats;
    private int isNotified;
    private Date timeStamp;

    public Subscription(Event event, int amountSeats, Date timeStamp) {
        this.id = 0;
        this.event = event;
        this.amountSeats = amountSeats;
        isNotified=0;
        this.timeStamp = timeStamp;
    }

    public Subscription() {
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

    public int getAmountSeats() {
        return amountSeats;
    }

    public void setAmountSeats(int amountSeats) {
        this.amountSeats = amountSeats;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getIsNotified() {
        return isNotified;
    }

    public void setIsNotified(int isNotified) {
        this.isNotified = isNotified;
    }
}

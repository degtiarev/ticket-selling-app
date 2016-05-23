package com.delexa.chudobilet.DBClasses;


import java.util.Date;

public class EventType {

    private int id;
    Event event;
    String type;
    private Date timeStamp;

    public EventType(Event event, String type, Date timeStamp) {
        this.event = event;
        this.type = type;
        this.timeStamp = timeStamp;
    }

    public EventType() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

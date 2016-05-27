package com.delexa.chudobilet.MainClasses;

import java.util.Date;

public class Establishment {

    private int id;
    private String name;
    private String address;
    private Date timeStamp;
    private String favouriteSeats;

    public Establishment() {
    }


    public Establishment( String name, String address, Date timeStamp, String favouriteSeats) {
        this.name = name;
        this.address = address;
        this.timeStamp = timeStamp;
        this.favouriteSeats = favouriteSeats;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFavouriteSeats() {
        return favouriteSeats;
    }

    public void setFavouriteSeats(String favouriteSeats) {
        this.favouriteSeats = favouriteSeats;
    }
}

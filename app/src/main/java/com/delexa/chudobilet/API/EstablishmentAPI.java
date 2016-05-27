package com.delexa.chudobilet.API;

import java.util.Date;

public class EstablishmentAPI {

    private int id;
    private String name;
    private String address;
    private Date timeStamp;
    private String favouriteSeats;

    public EstablishmentAPI() {
    }


    public EstablishmentAPI(String name, String address, Date timeStamp, String favouriteSeats) {
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

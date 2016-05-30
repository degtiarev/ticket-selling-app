package com.delexa.chudobilet.API;

import com.delexa.chudobilet.MainClasses.Establishment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EstablishmentAPI {

    private String name;
    private String address;
    private String timeStamp;

    public EstablishmentAPI() {
    }

    public EstablishmentAPI(String name, String address, String timeStamp) {
        this.name = name;
        this.address = address;
        this.timeStamp = timeStamp;
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Establishment getEstablishment() {
        Establishment establishment = new Establishment();

        establishment.setName(name);
        establishment.setAddress(address);

        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {

            Date curTimesStamp = formatter2.parse(timeStamp);
            establishment.setTimeStamp(curTimesStamp);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return establishment;
    }

}

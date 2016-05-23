package com.delexa.chudobilet.DBClasses;


import java.util.Date;

public class InterestEstablishment {

    private int id;
    private User user;
    protected Establishment establishment;
    private String seatNames;
    private Date timeStamp;

    public InterestEstablishment(User user, Establishment establishment, String seatNames, Date timeStamp) {
        this.user = user;
        this.establishment = establishment;
        this.seatNames = seatNames;
        this.timeStamp = timeStamp;
    }

    public InterestEstablishment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public String getSeatNames() {
        return seatNames;
    }

    public void setSeatNames(String seatNames) {
        this.seatNames = seatNames;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

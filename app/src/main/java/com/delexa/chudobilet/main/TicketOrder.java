package com.delexa.chudobilet.main;

import java.util.Date;

public class TicketOrder {

    private int id;
    private User user;
    private Seat seat;
    private Date purchaseDate;
    private String status;
    private String series;
    private String number;
    private String code;
    private Date timeStamp;

    public TicketOrder(User user, Seat seat, Date purchaseDate, String status, String series, String number, String code, Date timeStamp) {
        this.id = 0;
        this.user = user;
        this.seat = seat;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.series = series;
        this.number = number;
        this.code = code;
        this.timeStamp = timeStamp;
    }


    public TicketOrder() {
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

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

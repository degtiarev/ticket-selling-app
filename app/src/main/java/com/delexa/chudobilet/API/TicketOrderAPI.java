package com.delexa.chudobilet.API;

import com.delexa.chudobilet.MainClasses.User;

import java.util.Date;

public class TicketOrderAPI {

    private int id;
    private User user;
    private SeatAPI seatAPI;
    private Date purchaseDate;
    private String status;
    private String series;
    private String number;
    private String code;
    private Date timeStamp;

    public TicketOrderAPI(User user, SeatAPI seatAPI, Date purchaseDate, String status, String series, String number, String code, Date timeStamp) {
        this.id = 0;
        this.user = user;
        this.seatAPI = seatAPI;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.series = series;
        this.number = number;
        this.code = code;
        this.timeStamp = timeStamp;
    }


    public TicketOrderAPI() {
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

    public SeatAPI getSeatAPI() {
        return seatAPI;
    }

    public void setSeatAPI(SeatAPI seatAPI) {
        this.seatAPI = seatAPI;
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

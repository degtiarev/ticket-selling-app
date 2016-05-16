package com.delexa.chudobilet.DBClasses;

import java.util.Date;


public class Event {

    private int id;
    private String name;
    private Establishment establishment;
    private String country;
    private String genre;
    private int year;
    private String amountTime;
    private String forAge;
    private String roles;
    private String about;
    private byte[] cover;
    private String videoLink;
    private int isNotified;
    private  String link;
    private Date timeStamp;

    public Event(String name, Establishment establishment, String country, String genre, int year, String amountTime, String forAge, String roles, String about, byte[] cover, String videoLink, String link, Date timeStamp) {
        this.id = 0;
        this.name = name;
        this.establishment = establishment;
        this.country = country;
        this.genre = genre;
        this.year=year;
        this.amountTime = amountTime;
        this.forAge = forAge;
        this.roles = roles;
        this.about = about;
        this.cover = cover;
        this.videoLink = videoLink;
        this.link = link;
        isNotified=0;
        this.timeStamp = timeStamp;
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

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAmountTime() {
        return amountTime;
    }

    public void setAmountTime(String amountTime) {
        this.amountTime = amountTime;
    }

    public String getForAge() {
        return forAge;
    }

    public void setForAge(String forAge) {
        this.forAge = forAge;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getIsNotified() {
        return isNotified;
    }

    public void setIsNotified(int isNotified) {
        this.isNotified = isNotified;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

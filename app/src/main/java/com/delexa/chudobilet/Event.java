package com.delexa.chudobilet;

import java.util.Date;


public class Event {


    public Event(Establishment establishment, String name, String country, String genre, String amountTime, int forAge, String roles,
                 String about, byte[] cover, String videoLink, String link, Date timeStamp) {
        this.about = about;
        this.name = name;
        this.amountTime = amountTime;
        this.country = country;
        this.cover = cover;
        this.forAge = forAge;
        this.genre = genre;
        this.id = 0;
        this.establishment = establishment;
        this.roles = roles;
        this.timeStamp = timeStamp;
        this.videoLink = videoLink;
        this.link=link;
    }


    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAmountTime() {
        return amountTime;
    }

    public void setAmountTime(String amountTime) {
        this.amountTime = amountTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public int getForAge() {
        return forAge;
    }

    public void setForAge(int forAge) {
        this.forAge = forAge;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment name) {
        this.establishment = name;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private int id;
    private String name;
    private Establishment establishment;
    private String country;
    private String genre;
    private String amountTime;
    private int forAge;
    private String roles;
    private String about;
    private byte[] cover;
    private String videoLink;
    private  String link;
    private Date timeStamp;
}

package com.delexa.chudobilet.API;

public class EventAPI {

    private String name;
    private String country;
    private String genre;
    private int year;
    private String amountTime;
    private String forAge;
    private String roles;
    private String about;
    private String cover;
    private String videoLink;
    private String link;
    private String establishmentName;
    private String eventType;

    public EventAPI() {
    }

    public EventAPI(String cover, String name, String country, String genre, int year, String amountTime,
                    String forAge, String roles, String about, String videoLink, String link,
                    String establishmentName, String eventType) {
        this.cover = cover;
        this.name = name;
        this.country = country;
        this.genre = genre;
        this.year = year;
        this.amountTime = amountTime;
        this.forAge = forAge;
        this.roles = roles;
        this.about = about;
        this.videoLink = videoLink;
        this.link = link;
        this.establishmentName = establishmentName;
        this.eventType = eventType;
    }


    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
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

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


}

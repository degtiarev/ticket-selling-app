package com.delexa.chudobilet.MainClasses;


import java.util.Date;

public class News {

    private int id;
    private String name;
    private String about;
    private String inShort;
    private String cover;
    private Date date;
    private Date timeStamp;

    public News() {
    }

    public News(String name, String about, String inShort, String cover, Date date, Date timeStamp) {
        this.name = name;
        this.about = about;
        this.inShort = inShort;
        this.cover = cover;
        this.date = date;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getInShort() {
        return inShort;
    }

    public void setInShort(String inShort) {
        this.inShort = inShort;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

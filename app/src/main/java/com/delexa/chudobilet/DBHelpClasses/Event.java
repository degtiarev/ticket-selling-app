package com.delexa.chudobilet.DBHelpClasses;

import android.graphics.Bitmap;


public class Event {

    private int _id;
    private Bitmap cover;
    private String filmName;
    private String genre;
    private String time;
    private String country;
    private String forAge;
    private int year;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Bitmap getCover() {
        return cover;
    }

    public void setCover(Bitmap cover) {
        this.cover = cover;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getForAge() {
        return forAge;
    }

    public void setForAge(String forAge) {
        this.forAge = forAge;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

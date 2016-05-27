package com.delexa.chudobilet.API;


import com.delexa.chudobilet.MainClasses.News;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsAPI {

    private int id;
    private String name;
    private String about;
    private String inShort;
    private String cover;
    private String date;
    private String timeStamp;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public News getNews() {
        News news = new News();

        news.setId(id);
        news.setName(name);
        news.setAbout(about);
        news.setInShort(inShort);
        news.setCover(cover);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {

            Date curDate = formatter1.parse(date);
            news.setDate(curDate);

            Date curTimesStamp=formatter2.parse(timeStamp);
            news.setTimeStamp(curTimesStamp);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return news;
    }


}

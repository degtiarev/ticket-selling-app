package com.delexa.chudobilet.main;

import java.sql.Blob;
import java.util.Date;

public class User {

    private int id;
    private String name;
    private String family;
    private String patronymic;
    private String email;
    private Date date;
    private String sex;
    private String notificationToPay;
    private String emailNotificationChangeStatus;
    private String newsSubscriber;
    private String phone;
    private String password;
    private byte[] image;
    private String interestGenre;
    private String interestRoles;
    private String interestEstablishment;
    private Date timeStamp;

    public User(String name, String family, String patronymic, String email, Date date, String sex, String notificationToPay, String emailNotificationChangeStatus, String newsSubscriber, String phone, String password, byte[] image, String interestGenre, String interestRoles, String interestEstablishment, Date timeStamp) {
        this.id = 0;
        this.name = name;
        this.family = family;
        this.patronymic = patronymic;
        this.email = email;
        this.date = date;
        this.sex = sex;
        this.notificationToPay = notificationToPay;
        this.emailNotificationChangeStatus = emailNotificationChangeStatus;
        this.newsSubscriber = newsSubscriber;
        this.phone = phone;
        this.password = password;
        this.image = image;
        this.interestGenre = interestGenre;
        this.interestRoles = interestRoles;
        this.interestEstablishment = interestEstablishment;
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

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNotificationToPay() {
        return notificationToPay;
    }

    public void setNotificationToPay(String notificationToPay) {
        this.notificationToPay = notificationToPay;
    }

    public String getEmailNotificationChangeStatus() {
        return emailNotificationChangeStatus;
    }

    public void setEmailNotificationChangeStatus(String emailNotificationChangeStatus) {
        this.emailNotificationChangeStatus = emailNotificationChangeStatus;
    }

    public String getNewsSubscriber() {
        return newsSubscriber;
    }

    public void setNewsSubscriber(String newsSubscriber) {
        this.newsSubscriber = newsSubscriber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getInterestGenre() {
        return interestGenre;
    }

    public void setInterestGenre(String interestGenre) {
        this.interestGenre = interestGenre;
    }

    public String getInterestRoles() {
        return interestRoles;
    }

    public void setInterestRoles(String interestRoles) {
        this.interestRoles = interestRoles;
    }

    public String getInterestEstablishment() {
        return interestEstablishment;
    }

    public void setInterestEstablishment(String interestEstablishment) {
        this.interestEstablishment = interestEstablishment;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

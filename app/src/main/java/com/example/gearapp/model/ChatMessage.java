package com.example.gearapp.model;


public class ChatMessage {
    private String mess;
    private String date;
    private String dateobj;
    User user;

    public ChatMessage() {
    }

    public ChatMessage(String mess, String date, String dateobj, User user) {
        this.mess = mess;
        this.date = date;
        this.dateobj = dateobj;
        this.user = user;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateobj() {
        return dateobj;
    }

    public void setDateobj(String dateobj) {
        this.dateobj = dateobj;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

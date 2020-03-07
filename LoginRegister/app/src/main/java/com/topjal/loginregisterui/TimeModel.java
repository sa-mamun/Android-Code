package com.topjal.loginregisterui;

public class TimeModel {

    private int id;
    private String date;
    private String time;
    private int user_id;

    public TimeModel(String date, String time, int user_id) {
        this.date = date;
        this.time = time;
        this.user_id = user_id;
    }

    public TimeModel(int id, String date, String time, int user_id) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}

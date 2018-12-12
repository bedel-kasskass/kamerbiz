package com.bedel.app.kamerbiz.Entity;

import java.io.Serializable;
import java.util.Calendar;

public class Comment implements Serializable {
    public String id;
    public String text;
    public long date;
    public User user;

    public Comment() {
    }

    public Comment(String id, String text, User user) {
        this.id = id;
        this.text = text;
        this.date = Calendar.getInstance().getTimeInMillis();;
        this.user = user;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getUserId() {
        return user.getId();
    }

    public String getUserName() {
        return user.getUsername();
    }

    public String getUserPhoto() {
        return user.getPhoto();
    }
}

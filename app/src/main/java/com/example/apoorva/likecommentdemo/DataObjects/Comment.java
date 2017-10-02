package com.example.apoorva.likecommentdemo.DataObjects;

/**
 * Created by Apoorva on 28-Sep-17.
 */

public class Comment {

    String userid;
    String message;
    String username;

    public Comment() {
    }

    public Comment(String userid, String message, String username) {
        this.userid = userid;
        this.message = message;
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

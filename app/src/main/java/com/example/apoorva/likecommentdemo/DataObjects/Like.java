package com.example.apoorva.likecommentdemo.DataObjects;

/**
 * Created by Apoorva on 28-Sep-17.
 */

public class Like {

    private String userid;
    private String username;

    public Like() {
    }

    public Like(String userid, String username) {
        this.userid = userid;
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

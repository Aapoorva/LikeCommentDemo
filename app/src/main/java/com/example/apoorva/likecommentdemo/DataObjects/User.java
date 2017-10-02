package com.example.apoorva.likecommentdemo.DataObjects;

/**
 * Created by Apoorva on 30-Sep-17.
 */

public class User {
    String uid;
    String username;
    String displayname;

    public User() {
    }

    public User(String uid, String username, String displayname) {
        this.uid = uid;
        this.username = username;
        this.displayname = displayname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

}

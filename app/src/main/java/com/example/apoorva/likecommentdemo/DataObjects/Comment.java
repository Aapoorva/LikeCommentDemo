package com.example.apoorva.likecommentdemo.DataObjects;

/**
 * Created by Apoorva on 28-Sep-17.
 */

public class Comment {

    String userid;
    String commenttext;
    String username;

    public Comment() {
    }

    public Comment(String userid, String commenttext, String username) {
        this.userid = userid;
        this.commenttext = commenttext;
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCommenttext() {
        return commenttext;
    }

    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

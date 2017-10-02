package com.example.apoorva.likecommentdemo.DataObjects;

import java.util.HashMap;

/**
 * Created by Apoorva on 28-Sep-17.
 */

public class Task {

    private String tasksrcid;
    private String taskcreater;
    private String taskdesc;
    private int likeCount;
    private HashMap<String,String> likes;
    private HashMap<String,String> comments;

    public Task() {
    }

    public Task(String tasksrcid, String taskcreater, String taskdesc, HashMap<String,String> likes, HashMap<String,String> comments) {
        this.tasksrcid = tasksrcid;
        this.taskcreater = taskcreater;
        this.taskdesc = taskdesc;
        this.likes = likes;
        this.comments = comments;
    }

    public String getTasksrcid() {
        return tasksrcid;
    }

    public void setTasksrcid(String tasksrcid) {
        this.tasksrcid = tasksrcid;
    }

    public String getTaskcreater() {
        return taskcreater;
    }

    public void setTaskcreater(String taskcreater) {
        this.taskcreater = taskcreater;
    }

    public String getTaskdesc() {
        return taskdesc;
    }

    public void setTaskdesc(String taskdesc) {
        this.taskdesc = taskdesc;
    }

    public HashMap<String,String> getLikes() {
        return likes;
    }

    public void setLikes(HashMap<String,String> likes) {
        this.likes = likes;
    }

    public HashMap<String,String> getComments() {
        return comments;
    }

    public void setComments(HashMap<String,String> comments) {
        this.comments = comments;
    }

    public int getLikeCount() {
        setLikeCount();
        return likeCount;
    }

    public void setLikeCount() {
        if (likes!=null)
            likeCount = likes.size();
    }
}


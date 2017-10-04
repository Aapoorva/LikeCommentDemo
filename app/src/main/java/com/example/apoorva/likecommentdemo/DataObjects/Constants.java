package com.example.apoorva.likecommentdemo.DataObjects;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Apoorva on 30-Sep-17.
 */

public class Constants {

    public static final DatabaseReference REF_TASK_NODE = FirebaseDatabase.getInstance().getReference().child("task");
    public static final DatabaseReference ALL_USER_DB = FirebaseDatabase.getInstance().getReference().child("users");
    public static final FirebaseAuth AUTH_REFFERENCE = FirebaseAuth.getInstance();
    public static final FirebaseUser FIREBASE_USER = AUTH_REFFERENCE.getCurrentUser();
    public static final String LOG_TAG = "apoo123";
    public static final String LIKE_KEY = "likes";
    public static final String COMMENT_KEY = "comments";
    public static final String TASK_KEY = "task";
    public static final String USERNAME_KEY = "username";
    public static final String MESSAGE_KEY = "message";
}

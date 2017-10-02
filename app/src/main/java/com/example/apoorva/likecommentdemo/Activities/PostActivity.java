package com.example.apoorva.likecommentdemo.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.apoorva.likecommentdemo.R;

public class PostActivity extends AppCompatActivity {

    TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        data = (TextView)findViewById(R.id.data);

        Intent i = getIntent();

    }
}

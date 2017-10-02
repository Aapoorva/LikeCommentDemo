package com.example.apoorva.likecommentdemo.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apoorva.likecommentdemo.DataObjects.Comment;
import com.example.apoorva.likecommentdemo.DataObjects.Constants;
import com.example.apoorva.likecommentdemo.DataObjects.Task;
import com.example.apoorva.likecommentdemo.LayoutHelper.CommentViewHolder;
import com.example.apoorva.likecommentdemo.LayoutHelper.TaskViewHolder;
import com.example.apoorva.likecommentdemo.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RecyclerView taskView;
    RecyclerView commentView;
    DatabaseReference databaseTask , databaseLike, databaseComment;
    FirebaseUser user;
    String date;
    Comment newComment;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskView = (RecyclerView) findViewById(R.id.task_list);
        taskView.setLayoutManager(new LinearLayoutManager(this));

        databaseTask = FirebaseDatabase.getInstance().getReference().child(Constants.TASK_KEY);

        user = FirebaseAuth.getInstance().getCurrentUser();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //setting firebase Adapter

        FirebaseRecyclerAdapter<Task,TaskViewHolder> recyclerAdapter = new FirebaseRecyclerAdapter<Task, TaskViewHolder>(
                Task.class,
                R.layout.taskcard_layout,
                TaskViewHolder.class,
                databaseTask
        ) {
            @Override
            protected void populateViewHolder(final TaskViewHolder viewHolder, final Task task, int position) {
                //setting values to layout
                DatabaseReference taskRef = getRef(position);
                task.setTasksrcid(taskRef.getKey());
                viewHolder.setTask_creater(task.getTaskcreater());
                viewHolder.setTask_desc(task.getTaskdesc());
                viewHolder.setLike_count(task.getLikeCount()+"");
                if(task.getLikes() != null && task.getLikes().containsKey(user.getUid())){
                    //already liked
                    viewHolder.getLike_button().setImageResource(R.mipmap.like_cliked);
                }
                else{
                    //not liked
                    viewHolder.getLike_button().setImageResource(R.mipmap.like_unclicked);
                }

                viewHolder.getLike_button().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        likeButtonClick(task);
                    }
                });
                viewHolder.getComment_button().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        commentView = viewHolder.getCommentView();
                        setCommentVisible(task, viewHolder.getCommentLayout());
                        createComment(task ,viewHolder.getCommentMessageBox(),viewHolder.getPost_button());
                    }
                });
            }
        };

        taskView.setAdapter(recyclerAdapter);

    }

    private void likeButtonClick(Task task1) {

        databaseLike = databaseTask.child(task1.getTasksrcid()).child(Constants.LIKE_KEY);
        if( task1.getLikes() != null && task1.getLikes().containsKey(user.getUid())) {
            databaseLike.child(user.getUid()).removeValue();
            Toast.makeText(this, "Disliked", Toast.LENGTH_SHORT).show();
        }
        else{
            databaseLike.child(user.getUid()).setValue(user.getEmail());
            Toast.makeText(this, "Liked", Toast.LENGTH_SHORT).show();
        }
    }

    private void setCommentVisible(Task task1,LinearLayout commentLayout) {

        databaseComment = databaseTask.child(task1.getTasksrcid()).child(Constants.COMMENT_KEY);
        commentView.setLayoutManager(new LinearLayoutManager(this));
        setCommentAdapter();

        int visibility = commentLayout.getVisibility();
        commentLayout.setVisibility(visibility==View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    private void createComment(final Task task1, final EditText messageBox, Button postButton) {

        date = Calendar.getInstance().getTime() + "";

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseComment = databaseTask.child(task1.getTasksrcid()).child(Constants.COMMENT_KEY);
                message = messageBox.getText().toString().trim();
                if (TextUtils.isEmpty(message)){
                    Toast.makeText(MainActivity.this, "Write a Comment", Toast.LENGTH_SHORT).show();
                    return;
                }
                newComment = new Comment(user.getUid(),message,user.getEmail());
                databaseComment.child(date).setValue(newComment)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                                if (!task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "Cannot create comment", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void setCommentAdapter() {

        FirebaseRecyclerAdapter<Comment,CommentViewHolder> commentAdapter = new FirebaseRecyclerAdapter<Comment,CommentViewHolder>(
                Comment.class,
                R.layout.comment_layout,
                CommentViewHolder.class,
                databaseComment
        ){
            @Override
            protected void populateViewHolder(CommentViewHolder viewHolder, Comment model, int position) {
                viewHolder.setCommentCreater(model.getUsername());
                viewHolder.setCommentMessage(model.getMessage());
            }
        };
        commentView.setAdapter(commentAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.logout_menu){
            Constants.AUTH_REFFERENCE.signOut();
            Intent i = new Intent(this,Login.class);
            startActivity(i);
            finish();
            return true;
        }
        if(item.getItemId() == R.id.post){
            startActivity(new Intent(MainActivity.this,PostActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
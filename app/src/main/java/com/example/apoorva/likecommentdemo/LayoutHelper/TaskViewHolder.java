package com.example.apoorva.likecommentdemo.LayoutHelper;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apoorva.likecommentdemo.DataObjects.Comment;
import com.example.apoorva.likecommentdemo.DataObjects.Constants;
import com.example.apoorva.likecommentdemo.DataObjects.Task;
import com.example.apoorva.likecommentdemo.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;

/**
 * Created by Apoorva on 29-Sep-17.
 */

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private ImageButton like_button;
    private Button comment_button;
    private TextView task_creater,task_desc,like_count;
    private LinearLayout commentLayout;
    private EditText message_text;
    private RecyclerView commentView;
    private Button post_button;


    private DatabaseReference databaseComment;
    Context context;

    public TaskViewHolder(View itemView) {
        super(itemView);
        view = itemView;

        context = view.getContext();

        task_creater = (TextView) view.findViewById(R.id.task_creater);
        task_desc = (TextView) view.findViewById(R.id.task_desc);
        like_count = (TextView) view.findViewById(R.id.like_count);
        like_button = (ImageButton) view.findViewById(R.id.like_button);
        comment_button = (Button) view.findViewById(R.id.comment_button);


        commentLayout = (LinearLayout) view.findViewById(R.id.comment_layout);

        post_button = (Button) view.findViewById(R.id.post_button);
        message_text = (EditText) view.findViewById(R.id.comment_box);

        commentView = (RecyclerView) view.findViewById(R.id.commentView);
        commentView.setLayoutManager(new LinearLayoutManager(context));

    }

    public void setData(Task task){
        task_creater.setText(task.getTaskcreater());
        task_desc.setText(task.getTaskdesc());
    }

    public void setLike_count(String like_count) {
        this.like_count.setText(like_count);
    }

    public EditText getCommentMessageBox(){
        return message_text;
    }

    public ImageButton getLike_button() {
        return like_button;
    }

    public Button getComment_button() {
        return comment_button;
    }

    public LinearLayout getCommentLayout(){
        return commentLayout;
    }

    public void setCommentsVisible(){
        Log.e(Constants.LOG_TAG,"setting comment layout visibility");

        int visibility = commentLayout.getVisibility();
        commentLayout.setVisibility(visibility==View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    public void setCommentsVisible(int visibility){
        Log.e(Constants.LOG_TAG,"setting comment layout visibility");

        commentLayout.setVisibility(visibility);
    }

    public void setCommentAdapter(DatabaseReference databaseRef){
        Log.e(Constants.LOG_TAG,"setting comment layout adapter");

        databaseComment = databaseRef.child(Constants.COMMENT_KEY);
        FirebaseRecyclerAdapter<Comment,CommentViewHolder> recyclerAdapter = new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(
                Comment.class,
                R.layout.comment_layout,
                CommentViewHolder.class,
                databaseComment
        ) {
            @Override
            protected void populateViewHolder(CommentViewHolder viewHolder, Comment model, int position) {
                viewHolder.setData(model);
            }
        };
        commentView.setAdapter(recyclerAdapter);
    }

    public void setPostButtonClick(){
        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createComment();
            }
        });
    }


    public void createComment(){
        Log.e(Constants.LOG_TAG,"creating comment");

        String message = message_text.getText().toString().trim();
        if(TextUtils.isEmpty(message)){
            Toast.makeText(context, "Write a comment", Toast.LENGTH_SHORT).show();
            return;
        }
        Comment comment = new Comment(Constants.FIREBASE_USER.getUid(),message,Constants.FIREBASE_USER.getEmail());

        databaseComment.push().setValue(comment);
    }
}

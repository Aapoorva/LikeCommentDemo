package com.example.apoorva.likecommentdemo.LayoutHelper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.apoorva.likecommentdemo.R;

/**
 * Created by Apoorva on 02-Oct-17.
 */

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private TextView commentCreater;
    private TextView commentMessage;

    public CommentViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        commentCreater = (TextView) view.findViewById(R.id.comment_creater);
        commentMessage = (TextView) view.findViewById(R.id.comment_msg);
    }

    public void setCommentCreater(String commentCreater){
        this.commentCreater.setText(commentCreater);
    }

    public void setCommentMessage(String message){
        this.commentMessage.setText(message);
    }
}

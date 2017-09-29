package com.example.apoorva.likecommentdemo.LayoutHelper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.apoorva.likecommentdemo.DataObjects.Task;
import com.example.apoorva.likecommentdemo.R;

/**
 * Created by Apoorva on 29-Sep-17.
 */

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private ImageButton like_button;
    private Button comment_button;
    private TextView task_creater,task_desc,like_count;

    public TaskViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        task_creater = (TextView) view.findViewById(R.id.task_creater);
        task_desc = (TextView) view.findViewById(R.id.task_desc);
        like_count = (TextView) view.findViewById(R.id.like_count);
        like_button = (ImageButton) view.findViewById(R.id.like_button);
        comment_button = (Button) view.findViewById(R.id.comment_button);
    }

    public void setTask_creater(String task_creater) {
        this.task_creater.setText(task_creater);
    }

    public void setTask_desc(String task_desc) {
        this.task_desc.setText(task_desc);
    }

    public void setLike_count(String like_count) {
        this.like_count.setText(like_count);
    }

    public ImageButton getLike_button() {
        return like_button;
    }

    public Button getComment_button() {
        return comment_button;
    }
}

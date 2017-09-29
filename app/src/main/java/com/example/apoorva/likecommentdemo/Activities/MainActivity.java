package com.example.apoorva.likecommentdemo.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.apoorva.likecommentdemo.DataObjects.Task;
import com.example.apoorva.likecommentdemo.LayoutHelper.TaskViewHolder;
import com.example.apoorva.likecommentdemo.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    RecyclerView taskView;
    DatabaseReference databaseTask , databaseLike, databaseComment;
    FirebaseUser user;
    boolean liked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskView = (RecyclerView) findViewById(R.id.task_list);
        taskView.setLayoutManager(new LinearLayoutManager(this));

        databaseTask = FirebaseDatabase.getInstance().getReference().child("task");

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
            protected void populateViewHolder(TaskViewHolder viewHolder, final Task task, int position) {

                //setting values to layout
                DatabaseReference taskRef = getRef(position);
                task.setTasksrcid(taskRef.getKey());
                viewHolder.setTask_creater(task.getTaskcreater());
                viewHolder.setTask_desc(task.getTaskdesc());
                viewHolder.setLike_count(task.getLikeCount()+"");
                viewHolder.getLike_button().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        likeButtonClick(task);
                    }
                });
            }
        };

        taskView.setAdapter(recyclerAdapter);
    }

    private void likeButtonClick(final Task task) {

        databaseLike = databaseTask.child(task.getTasksrcid()).child("likes").getRef();

        databaseLike.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(user.getUid())){
                    dataSnapshot.child(user.getUid()).getRef().removeValue();
                }
                else{

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
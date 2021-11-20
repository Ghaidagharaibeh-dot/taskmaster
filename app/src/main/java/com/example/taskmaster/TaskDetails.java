package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        String taskName=getIntent().getStringExtra("taskname");
        TextView tasktitle=findViewById(R.id.titleDetails);
        tasktitle.setText(taskName);


        String taskBody=getIntent().getStringExtra("taskBody");
        TextView body=findViewById(R.id.detailsBody);
        body.setText(taskBody);

        String taskState=getIntent().getStringExtra("taskState");
        TextView state=findViewById(R.id.detailsState);
        state.setText(taskState);
    }


}
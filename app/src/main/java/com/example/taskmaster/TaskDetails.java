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


    }


}
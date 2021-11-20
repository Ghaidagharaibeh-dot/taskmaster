package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addButton= (Button) findViewById(R.id.addTaskButton);
        Button allTaskButton=(Button) findViewById(R.id.AllTaskButton);
        Button settingButton=(Button) findViewById(R.id.settingButton);
        Button task1Button=(Button) findViewById(R.id.button1);
        Button task2Button=(Button) findViewById(R.id.button2);
        Button task3Button=(Button) findViewById(R.id.button3);

        String task1String=task1Button.getText().toString();
        String task2String=task2Button.getText().toString();
        String task3String=task3Button.getText().toString();

        task1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskDetails.class);
                intent.putExtra("taskname",task1String);
                v.getContext().startActivity(intent);
            }
        });


        task2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskDetails.class);
                intent.putExtra("taskname",task2String);
                v.getContext().startActivity(intent);
            }
        });


        task3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskDetails.class);
                intent.putExtra("taskname",task3String);
                v.getContext().startActivity(intent);
            }
        });




        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddTask.class));
            }
        });

        allTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AllTasks.class));

            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Setting.class));

            }
        });

    }
    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String user = sharedPreferences.getString("userNme" , "user Task");


        TextView nameLable=findViewById(R.id.nameLable);
        nameLable.setText(user + " tasks");




    }

}

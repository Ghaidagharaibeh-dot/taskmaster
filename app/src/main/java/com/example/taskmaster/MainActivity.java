package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        List<Task> tasks = new ArrayList<>();
//        tasks.add(new Task("student", "i need to study", "assigned"));
//        tasks.add(new Task("Reading", "i need to read", "assigned"));
//        tasks.add(new Task("Walking", "i need to walk", "assigned"));
        try {
            //Amplify.addPlugin(new AWSApiPlugin()); // UNCOMMENT this line once backend is deployed
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Amplify", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("Amplify", "Could not initialize Amplify", error);
        }

//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                TaskDataBase db = Room.databaseBuilder(getApplicationContext(),TaskDataBase.class,"data").build();
//                TaskDao taskDao= db.taskDao();
//                List<Task> tasks = taskDao.getAll();
                List tasks =new ArrayList();
                Amplify.DataStore.query(
                        Task.class,
                        items -> {
                            while (items.hasNext()) {
                                Task item = items.next();
                                tasks.add(item);
                                Log.i("Amplify", "Id " + item.getId());
                            }
                        },
                        failure -> Log.e("Amplify", "Could not query DataStore", failure)
                );

                RecyclerView recyclerView = findViewById(R.id.taskReview);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(new TaskAdapter(tasks));
//            }
//        }
//        );




        Button addButton = (Button) findViewById(R.id.addTaskButton);
        Button allTaskButton = (Button) findViewById(R.id.AllTaskButton);
        Button settingButton = (Button) findViewById(R.id.settingButton);

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
                startActivity(new Intent(MainActivity.this, Setting.class));

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String user = sharedPreferences.getString("userNme", "user Task");


        TextView nameLable = findViewById(R.id.nameLable);
        nameLable.setText(user + " tasks");


    }
}

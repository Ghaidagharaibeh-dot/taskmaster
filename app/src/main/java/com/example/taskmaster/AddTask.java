package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        TextView title= findViewById(R.id.editName);
        TextView body=findViewById(R.id.editBody);
        Button addTasks= (Button) findViewById(R.id.addTasks);

        addTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String titleString = title.getText().toString();
                        String bodyString = body.getText().toString();
//                        TaskDataBase db = Room.databaseBuilder(getApplicationContext(),TaskDataBase.class,"data").build();
//                        TaskDao taskDao= db.taskDao();
//                        Task entry= new Task(titleString,bodyString,"assigned");
//                        taskDao.insert(entry);
                        Task item = Task.builder()
                                .title(titleString)
                                .body(bodyString)
                                .state("active")
                                .build();
                        Amplify.DataStore.save(
                                item,
                                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                                error -> Log.e("Amplify", "Could not save item to DataStore", error)
                        );
                    }
                });

                Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_LONG).show();
            }
        });
    }



}
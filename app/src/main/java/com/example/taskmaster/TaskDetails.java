package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import java.io.File;

public class TaskDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        String taskName = getIntent().getStringExtra("taskname");
        TextView tasktitle = findViewById(R.id.titleDetails);
        tasktitle.setText(taskName);


        String taskBody = getIntent().getStringExtra("taskBody");
        TextView body = findViewById(R.id.detailsBody);
        body.setText(taskBody);

        String taskState = getIntent().getStringExtra("taskState");
        TextView state = findViewById(R.id.detailsState);
        state.setText(taskState);

        Bundle extras = getIntent().getExtras();

        String img = extras.getString("img");
        Amplify.Storage.downloadFile(
                "image",
                new File(getApplicationContext().getFilesDir() + "/download.jpg"),
                result -> {
                    ImageView image = findViewById(R.id.imgeViewIdDetail);
                    extras.getString("img");
                    image.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));

                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile());
                },
                error -> Log.e("MyAmplifyApp", "Download Failure", error)
        );
    }
}


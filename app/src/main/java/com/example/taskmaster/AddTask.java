package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        TextView title = findViewById(R.id.sp);
        TextView body = findViewById(R.id.editBody);
        Button addTasks = (Button) findViewById(R.id.addTasks);

//        Team item1 = Team.builder()
//                .name("team1")
//                .build();
//        Amplify.DataStore.save(
//                item1,
//                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
//                error -> Log.e("Amplify", "Could not save item to DataStore", error)
//        );
//
//        Team item2 = Team.builder()
//                .name("team2")
//                .build();
//        Amplify.DataStore.save(
//                item2,
//                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
//                error -> Log.e("Amplify", "Could not save item to DataStore", error)
//        );
//
//        Team item3 = Team.builder()
//                .name("team3")
//                .build();
//        Amplify.DataStore.save(
//                item3,
//                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
//                error -> Log.e("Amplify", "Could not save item to DataStore", error)
//        );

        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        addTasks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String titleString = title.getText().toString();
                String bodyString = body.getText().toString();
                String team = s.getSelectedItem().toString();

                Amplify.DataStore.query(
                        Team.class, Team.NAME.contains(team),
                        items -> {
                            while (items.hasNext()) {
                                Team item = items.next();
                                Task item1 = Task.builder().title(titleString).body(bodyString).state("Active").teamId(item.getId()).build();
                                Amplify.DataStore.save(
                                        item1,
                                        success -> Log.i("COMO", "Saved item: "),
                                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
                                );
                                Log.i("EMAM", "Id was stored ");
                                Log.i("Amplify", "Id " + item.getId());
                            }
                        },
                        failure -> Log.e("Amplify", "Could not query DataStore", failure)
                );


                Toast.makeText(getApplicationContext(), "Task Added", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}

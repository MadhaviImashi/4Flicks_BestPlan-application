package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class UpdateTask extends AppCompatActivity {

    Button btnSaveTask, newBtn;
    private EditText time, desc;
    //declare variables to assign the data comes from the list item when user clicks on Edit btn
    private String timeToBeUpdated, descToBeUpdated, IDofSelectedTask;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        btnSaveTask = findViewById(R.id.btnSaveTask);
        time = findViewById(R.id.editTextTime);
        desc = findViewById(R.id.editTextDescription);
        context = this;

        //get the data belongs to the clicked list item from the list activity
        Intent newIntent = getIntent();
        IDofSelectedTask = newIntent.getStringExtra("id"); //get the ID of the user selected tast view
        timeToBeUpdated = newIntent.getStringExtra("Time");
        descToBeUpdated = newIntent.getStringExtra("Description");

        //set those data in the edit text views
        time.setText(timeToBeUpdated);
        desc.setText(descToBeUpdated);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayListofUpdatedData(v);
            }
        });
    }

   public void displayListofUpdatedData(View view){
        //create the variables to store user updated data
        String updatedTime = time.getText().toString();
        String updatedDescription = desc.getText().toString();

        DailyTaskModel modelObj = new DailyTaskModel(Integer.valueOf(IDofSelectedTask), updatedTime, updatedDescription, 0, 0 );

        DbHandlerDailyTasks handler = new DbHandlerDailyTasks(context);
        handler.updateTask(modelObj);

        Intent i = new Intent(this, ToDoList.class);
        startActivity(i);
    }

}
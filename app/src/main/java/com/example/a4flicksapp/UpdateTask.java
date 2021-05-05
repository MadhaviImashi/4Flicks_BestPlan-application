package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    private String timeToBeUpdated, descToBeUpdated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        btnSaveTask = findViewById(R.id.btnSaveTask);
        time = findViewById(R.id.editTextTime);
        desc = findViewById(R.id.editTextDescription);

        //get the data belongs to the clicked list item from the list activity
        Intent newIntent = getIntent();
        timeToBeUpdated = newIntent.getStringExtra("Time");
        descToBeUpdated = newIntent.getStringExtra("Description");

        //set those data in the edit text views
        time.setText(timeToBeUpdated);
        desc.setText(descToBeUpdated);
    }


}
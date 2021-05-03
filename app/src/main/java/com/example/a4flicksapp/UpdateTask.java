package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class UpdateTask extends AppCompatActivity {

    Button btnSaveTask, newBtn;
    private EditText time, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        btnSaveTask = findViewById(R.id.btnSaveTask);
        time = findViewById(R.id.editTextTime);
        desc = findViewById(R.id.editTextDescription);

    }


}
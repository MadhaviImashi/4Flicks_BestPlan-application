package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentedTips extends AppCompatActivity {

    Button btnNavDailyPlan, btnUnderW, btnNormal, btnOverW; //create button objects for fragment buttons
    Fragment frgUnderW, frgNormal, frgOverW; //fragment objects

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmented_tips);

        btnUnderW = findViewById(R.id.btnUnderW);
        btnNormal = findViewById(R.id.btnNormal);
        btnOverW = findViewById(R.id.btnOverW);
        btnNavDailyPlan = findViewById(R.id.btnNavTasksList);

        //instantiate objects for each fragment.java class
        frgUnderW = new underweightFragment();
        frgNormal = new normalFragment();
        frgOverW = new overweightFragment();

        //set onclick listener to link the btn with the next actvity
        btnNavDailyPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayTasksList(v);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        btnUnderW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frgBody, frgUnderW).commit(); //replace the fragment body part with the frgUnderW fragment
            }
        });

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frgBody, frgNormal).commit();
            }
        });

        btnOverW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frgBody, frgOverW).commit();
            }
        });
    }

    public void displayTasksList(View view){
        Intent intent = new Intent(this, ToDoList.class);
        startActivity(intent);
    }
}
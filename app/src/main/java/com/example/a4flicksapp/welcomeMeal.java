package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class welcomeMeal extends AppCompatActivity {

    private ImageButton homesm;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_meal);
        homesm= findViewById(R.id.mlplhome);


        homesm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,MainActivity.class));
            }
        });

    }

    public void displayaddmeal2(View view){

        Intent intent = new Intent(this, listOfMeals.class);

        startActivity(intent);
    }

    public void displaycalorie(View view){

        Intent intent = new Intent(this, calorCalcu.class);

        startActivity(intent);
    }




}
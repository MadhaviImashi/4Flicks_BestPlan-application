package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class welcomeMeal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_meal);
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
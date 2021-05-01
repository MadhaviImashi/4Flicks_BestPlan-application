package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnNavBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNavBMI = findViewById(R.id.btnNavBMI);
    }

    public void displayBMIactivity(View view){

        Intent intent = new Intent(this, bmiCalculator.class);

        startActivity(intent);
    }

    //create onclick method to link grocery list button
    public void goToGroceryListHome(View view) {
        Intent intent = new Intent(this, GroceryListHome.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Welcome To Best Plan Grocery List", Toast.LENGTH_SHORT).show();
    }
}
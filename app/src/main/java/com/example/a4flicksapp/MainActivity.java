package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnNavBMI;
    Button btnGroceryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNavBMI = findViewById(R.id.btnNavBMI);
        btnGroceryList = findViewById(R.id.btnGrocery);

        //create onclick method to navigate to grocery list welcome view
        btnGroceryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GroceryListHome.class);
                startActivity(intent);
            }
        });
    }

    public void displayBMIactivity(View view){

        Intent intent = new Intent(this, bmiCalculator.class);

        startActivity(intent);
    }
}
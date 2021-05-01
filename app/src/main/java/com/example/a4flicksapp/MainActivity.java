package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public void displayAllRecipesWithRecipes(View view){
        Intent intent = new Intent(this, all_recipes_with_recipes.class);

        startActivity(intent);
    }
}
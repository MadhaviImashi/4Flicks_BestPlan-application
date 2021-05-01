package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class all_recipes_with_recipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipes_with_recipes);
    }

    public void displayAllRecipesWithRecipes(View view){
        Intent intent = new Intent(this, create_recipe.class);
        startActivity(intent);
    }
}
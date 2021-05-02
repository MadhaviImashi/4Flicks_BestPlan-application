package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class single_recipe_card extends AppCompatActivity {

    private ImageView IconOfRecipe;
    private TextView NameOfRecipeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe_card);

        IconOfRecipe = findViewById(R.id.recipeIcon);
        NameOfRecipeIcon = findViewById(R.id.recipeNameIcon);
    }
}
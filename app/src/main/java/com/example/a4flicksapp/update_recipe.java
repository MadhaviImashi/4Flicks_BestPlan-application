package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class update_recipe extends AppCompatActivity {

    private EditText EditRecipeName;
    private EditText EditRecipeIngredients;
    private EditText EditDirections;
    private Button RecipeUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);

        EditRecipeName = findViewById(R.id.EditRecipeName);
        EditRecipeIngredients = findViewById(R.id.EditRecipeIngredients);
        EditDirections = findViewById(R.id.EditDirections);
        RecipeUpdate = findViewById(R.id.recipeUpdate);
    }
}
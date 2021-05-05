package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

public class create_recipe extends AppCompatActivity {

    private TextInputEditText RecipeName;
    private  TextInputEditText RecipeIngredients;
    private  TextInputEditText RecipeDirections;
    private  Button SubmitRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        RecipeName = findViewById(R.id.editRecipeName);
        RecipeIngredients = findViewById(R.id.editRecipeIngredients);
        RecipeDirections = findViewById(R.id.editDirections);
        SubmitRecipe = findViewById(R.id.recipeSubmit);
    }
}
package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

public class update_recipe extends AppCompatActivity {

    private TextInputEditText EditRecipeName;
    private TextInputEditText EditRecipeIngredients;
    private TextInputEditText EditDirections;
    private Button ImageEdit;
    private ImageView EditRecipeInputImage;
    private Button RecipeUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);

        EditRecipeName = findViewById(R.id.editRecipeName);
        EditRecipeIngredients = findViewById(R.id.editRecipeIngredients);
        EditDirections = findViewById(R.id.editDirections);
        ImageEdit = findViewById(R.id.imageEdit);
        EditRecipeInputImage = findViewById(R.id.editRecipeInputImage);
        RecipeUpdate = findViewById(R.id.recipeUpdate);
    }
}
package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class recipe extends AppCompatActivity {

    private TextView NameOfRecipe;
    private TextView IngredientsOfRecipe;
    private TextView StepsOfRecipe;
    private RecipeDbHandler recipeDbHandler;
    private Context context;
    private Long updatedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        context = this;
        recipeDbHandler = new RecipeDbHandler(context);

        NameOfRecipe = findViewById(R.id.RecipeName);
        IngredientsOfRecipe = findViewById(R.id.ingredientsList);
        StepsOfRecipe = findViewById(R.id.Steps);

        final String id = getIntent().getStringExtra("id");
        recipeModel recipeModel = recipeDbHandler.getSingleRecipe(Integer.parseInt(id));

        NameOfRecipe.setText(recipeModel.getRecipe_Name());
        IngredientsOfRecipe.setText(recipeModel.getRecipe_Ingredients());
        StepsOfRecipe.setText(recipeModel.getRecipe_Directions());
    }

    public void displayUpdateActivity(View view){
        Intent intent = new Intent(this, update_recipe.class);
        startActivity(intent);
    }
}
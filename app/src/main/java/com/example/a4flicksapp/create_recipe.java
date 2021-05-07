package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class create_recipe extends AppCompatActivity {

    private EditText RecipeName;
    private EditText RecipeIngredients;
    private EditText RecipeDirections;
    private  Button SubmitRecipe;
    private RecipeDbHandler recipeDbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        RecipeName = (EditText) findViewById(R.id.CRecipeName);
        RecipeIngredients = (EditText) findViewById(R.id.CRecipeIngredients);
        RecipeDirections = (EditText)findViewById(R.id.CDirections);
        SubmitRecipe = (Button) findViewById(R.id.recipeSubmit);

        context = this;
        recipeDbHandler = new RecipeDbHandler(context);

        SubmitRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Recipe_Name = RecipeName.getText().toString();
                String Recipe_Ingredients = RecipeIngredients.getText().toString();
                String Recipe_Directions = RecipeDirections.getText().toString();
                long started = System.currentTimeMillis();

                recipeModel RecipeModel = new recipeModel(Recipe_Name, Recipe_Ingredients, Recipe_Directions, started,0);
                recipeDbHandler.createRecipe(RecipeModel);

                Toast.makeText(create_recipe.this, "Recipe Added", Toast.LENGTH_LONG).show();
                startActivity(new Intent(context, all_recipes_with_recipes.class));

            }
        });
    }
}
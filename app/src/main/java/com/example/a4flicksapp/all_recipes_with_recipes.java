package com.example.a4flicksapp;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class all_recipes_with_recipes extends AppCompatActivity {

    private ImageButton AddRecipe;
    private ListView RecipeList;
    private TextView NoOfRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipes_with_recipes);

        AddRecipe = findViewById(R.id.addRecipeBtn);
        RecipeList = findViewById(R.id.recipeList);
        NoOfRecipes = findViewById(R.id.noOfRecipes);
    }

    public void displayAllRecipesWithRecipes(View view){
        Intent intent = new Intent(this, create_recipe.class);
        startActivity(intent);
    }


}
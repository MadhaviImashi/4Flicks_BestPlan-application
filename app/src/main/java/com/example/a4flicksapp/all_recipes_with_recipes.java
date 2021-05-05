package com.example.a4flicksapp;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class all_recipes_with_recipes extends AppCompatActivity {

    private ImageButton AddRecipe;
    private ListView RecipeList;
    private TextView NoOfRecipes;
    Context context;
    private RecipeDbHandler recipeDbHandler;
    private List<recipeModel> recipeModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipes_with_recipes);

        recipeDbHandler = new RecipeDbHandler(this);
        AddRecipe = findViewById(R.id.addRecipeBtn);
        RecipeList = findViewById(R.id.recipeList);
        NoOfRecipes = findViewById(R.id.noOfRecipes);
        context = this;
        recipeModels = new ArrayList<>();

        recipeModels = recipeDbHandler.getAllRecipes();

        recipeAdapter adapter = new recipeAdapter(context, R.layout.activity_single_recipe_card, recipeModels);
        RecipeList.setAdapter(adapter);

        //get no of recipes from the table
        int countRecipes = recipeDbHandler.countRecipes();
        NoOfRecipes.setText("You have "+countRecipes+" Recipes");

        AddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, create_recipe.class));
            }
        });
    }

    public void displayAllRecipesWithRecipes(View view){
        Intent intent = new Intent(this, create_recipe.class);
        startActivity(intent);
    }

}
package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class recipe extends AppCompatActivity {

    private TextView NameOfRecipe;
    private ImageView ImageOfRecipe;
    private TextView IngredientsOfRecipe;
    private TextView StepsOfRecipe;
    private Button UpdateRecipeBtn;
    private Button DeleteRecipeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        NameOfRecipe = findViewById(R.id.RecipeName);
        ImageOfRecipe = findViewById(R.id.RecipeImage);
        IngredientsOfRecipe = findViewById(R.id.ingredientsList);
        StepsOfRecipe = findViewById(R.id.Steps);
        UpdateRecipeBtn = findViewById(R.id.Updatebtn);
        DeleteRecipeBtn = findViewById(R.id.Deletebtn);
    }

    public void displayUpdateActivity(View view){
        Intent intent = new Intent(this, update_recipe.class);
        startActivity(intent);
    }
}
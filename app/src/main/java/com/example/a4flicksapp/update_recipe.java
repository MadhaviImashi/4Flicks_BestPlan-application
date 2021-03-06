package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class update_recipe extends AppCompatActivity {

    private EditText EditRecipeName;
    private EditText EditRecipeIngredients;
    private EditText EditDirections;
    private Button RecipeUpdate;
    private RecipeDbHandler recipeDbHandler;
    private Context context;
    private Long updatedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);


        context = this;
        recipeDbHandler = new RecipeDbHandler(context);

        EditRecipeName = findViewById(R.id.EditRecipeName);
        EditRecipeIngredients = findViewById(R.id.EditRecipeIngredients);
        EditDirections = findViewById(R.id.EditDirections);
        RecipeUpdate = findViewById(R.id.recipeUpdate);

        final String id = getIntent().getStringExtra("id");
        recipeModel recipeModel = recipeDbHandler.getSingleRecipe(Integer.parseInt(id));

        EditRecipeName.setText(recipeModel.getRecipe_Name());
        EditRecipeIngredients.setText(recipeModel.getRecipe_Ingredients());
        EditDirections.setText(recipeModel.getRecipe_Directions());

        RecipeUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Make sure that Recipe Name field is not empty(Validation)
                if (TextUtils.isEmpty(EditRecipeName.getText())){
                    EditRecipeName.setError("Recipe Name is Required");
                    EditRecipeName.requestFocus();
                }

                //Make sure that Recipe Ingredients field is not empty(Validation)
                if (TextUtils.isEmpty(EditRecipeIngredients.getText())){
                    EditRecipeIngredients.setError("Recipe Ingredients are Required");
                    EditRecipeIngredients.requestFocus();
                }
                //Make sure that Recipe Directions field is not empty(Validation)
                if (TextUtils.isEmpty(EditDirections.getText())){
                    EditDirections.setError("Recipe Directions are Required");
                    EditDirections.requestFocus();
                }

                else {

                    String EditedRecipeName = EditRecipeName.getText().toString();
                    String EditedRecipeIngredients = EditRecipeIngredients.getText().toString();
                    String EditedDirections = EditDirections.getText().toString();
                    updatedDate = System.currentTimeMillis();

                    recipeModel recipeModel = new recipeModel(Integer.parseInt(id), EditedRecipeName, EditedRecipeIngredients, EditedDirections, updatedDate, 0);
                    int state = recipeDbHandler.updateRecipe(recipeModel);
                    Toast.makeText(update_recipe.this, "Updated the Recipe", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, all_recipes_with_recipes.class));

                }
            }
        });
    }
}
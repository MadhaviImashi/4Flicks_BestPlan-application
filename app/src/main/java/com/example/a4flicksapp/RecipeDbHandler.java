package com.example.a4flicksapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RecipeDbHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "BestPlanDatabase";
    private static final String TABLE_NAME = "recipe";


    //Column Names
    private static final String ID  = "id";
    private static final String RECIPE_NAME  = "Recipe_Name";
    private static final String RECIPE_INGREDIENTS  = "Recipe_Ingredients";
    private static final String RECIPE_DIRECTIONS  = "Recipe_Directions";
    private static final String STARTED  = "started";
    private static final String FINISHED  = "finished";


    public RecipeDbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" "+
                "("
                + ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + RECIPE_NAME +" TEXT,"
                + RECIPE_INGREDIENTS +" TEXT,"
                + RECIPE_DIRECTIONS +" TEXT,"
                + STARTED +" TEXT,"
                + FINISHED +" TEXT" +
                ");";
        /* CREATE TABLE recipe(id INTEGER PRIMARY KEY AUTOINCREMENT, Recipe_Name TEXT, Recipe_Ingredients TEXT, Recipe_Directions TEXT) */

        db.execSQL(TABLE_CREATE_QUERY);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        //Drops the older table if exists
        db.execSQL(DROP_TABLE_QUERY);
        //Creates the table again
        onCreate(db);

    }

    //method to insert recipe details to recipe table
    public void createRecipe(recipeModel RecipeModel){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(RECIPE_NAME, RecipeModel.getRecipe_Name());
        contentValues.put(RECIPE_INGREDIENTS, RecipeModel.getRecipe_Ingredients());
        contentValues.put(RECIPE_DIRECTIONS, RecipeModel.getRecipe_Directions());
        contentValues.put(STARTED, RecipeModel.getStarted());
        contentValues.put(FINISHED, RecipeModel.getFinished());

        //Save above data to recipe table
        sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        //close database
        sqLiteDatabase.close();

    }

    //method to count the number of recipes
    public int countRecipes(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }

    //Get all recipes into a list
    public List<recipeModel> getAllRecipes(){
        List<recipeModel> recipeModels = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        //values will only be added to list if only there are data
        if(cursor.moveToFirst()){
            do {
                //Create a new recipeModel object
                recipeModel recipeModel = new recipeModel();
                //
                recipeModel.setId(cursor.getInt(0));
                recipeModel.setRecipe_Name(cursor.getString(1));
                recipeModel.setRecipe_Ingredients(cursor.getString(2));
                recipeModel.setRecipe_Directions(cursor.getString(3));
                recipeModel.setStarted(cursor.getLong(4));
                recipeModel.setFinished(cursor.getLong(5));

                //recipes = obj.objs
                recipeModels.add(recipeModel);
            }
            while (cursor.moveToNext());
        }
        return recipeModels;
    }

}

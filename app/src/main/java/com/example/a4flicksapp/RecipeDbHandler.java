package com.example.a4flicksapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}

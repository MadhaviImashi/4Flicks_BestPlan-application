package com.example.a4flicksapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RecipeDbHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "BestPlanDatabase";
    private static final String TABLE_NAME = "recipe";
    public RecipeDbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /* CREATE TABLE recipe(id INTEGER PRIMARY KEY AUTOINCREMENT, Recipe_Name TEXT, Recipe_Ingredients TEXT, Recipe_Directions TEXT, Recipe_Image ) */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

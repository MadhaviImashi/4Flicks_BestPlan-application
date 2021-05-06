package com.example.a4flicksapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBhandlerMeal extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "BestPlanDatabase";
    private static final String TABLE_NAME = "mealPlanner";

    private static final String ID1 = "id1";
    private static final String DAY1 = "day1";
    private static final String BREAKFIRST1 = "breakfirst1";
    private static final String LUNCH1 = "lunch1";
    private static final String DINNER1 = "dinner1";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    public DBhandlerMeal(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " " +
                "("
                + ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DAY1 + " TEXT,"
                + BREAKFIRST1 + " TEXT,"
                + LUNCH1 + " TEXT,"
                + DINNER1 + " TEXT,"
                + STARTED + " TEXT,"
                + FINISHED + " TEXT" +
                ");";
        db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        // Drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        // Create tables again
        onCreate(db);

    }

    public void addMealplan(mealPlan meals1) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DAY1, meals1.getDay1());
        contentValues.put(BREAKFIRST1, meals1.getBreakfirst1());
        contentValues.put(LUNCH1, meals1.getLunch1());
        contentValues.put(DINNER1, meals1.getDinner1());
        contentValues.put(STARTED, meals1.getStarted());
        contentValues.put(FINISHED, meals1.getFinished());
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

    }
    //counting number of meal function
    public int countmeals() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }
    //list of meal function
    public List<mealPlan> getAllMeals() {

        List<mealPlan> meals3 = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                // Create new meal object
                mealPlan meals2 = new mealPlan();
                // mmgby6hh
                meals2.setId1(cursor.getInt(0));
                meals2.setDay1(cursor.getString(1));
                meals2.setBreakfirst1(cursor.getString(2));
                meals2.setLunch1(cursor.getString(3));
                meals2.setDinner1(cursor.getString(4));
                meals2.setStarted(cursor.getLong(5));
                meals2.setFinished(cursor.getLong(6));


                meals3.add(meals2);
            } while (cursor.moveToNext());
        }
        return meals3;
    }

    // Delete meal function
    public void deleteMeal(int id1) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "id1 =?", new String[]{String.valueOf(id1)});
        db.close();

    }


    public mealPlan getSingleMeal(int id1){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ID1,DAY1,BREAKFIRST1,LUNCH1,DINNER1,STARTED, FINISHED},
                ID1 + "= ?",new String[]{String.valueOf(id1)}
                ,null,null,null);

        mealPlan meal6;
        if(cursor != null){
            cursor.moveToFirst();
            meal6 = new mealPlan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getLong(5),
                    cursor.getLong(6)
            );
            return meal6;
        }
        return null;
    }
    //update meal function
    public int updateSingleMeal(mealPlan meals1){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DAY1, meals1.getDay1());
        contentValues.put(BREAKFIRST1, meals1.getBreakfirst1());
        contentValues.put(LUNCH1, meals1.getLunch1());
        contentValues.put(DINNER1, meals1.getDinner1());
        contentValues.put(STARTED, meals1.getStarted());
        contentValues.put(FINISHED, meals1.getFinished());

        int status = db.update(TABLE_NAME,contentValues,ID1 +" =?",
                new String[]{String.valueOf(meals1.getId1())});

        db.close();
        return status;
    }
}
package com.example.a4flicksapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHandlerGrocery extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="BestPlanDatabase.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="grocery";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="item_name";
    private static final String COLUMN_PRICE="price";
    private static final String COLUMN_QUANTITY="quantity";

    public DBHandlerGrocery(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " FLOAT, " +
                COLUMN_QUANTITY + " INTEGER); " ;

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //add new grocery item to the database
    void addGroceryItem(String item_name, float price, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,item_name);
        cv.put(COLUMN_PRICE,price);
        cv.put(COLUMN_QUANTITY,quantity);
        long result = db.insert(TABLE_NAME,null,cv);
        if (result == -1){
            Toast.makeText(context,"Failed to Add New Grocery Item",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Successfully Added",Toast.LENGTH_SHORT).show();
        }
    }

    //read all data stored in SQLite database
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    //update grocery item
    void updateItemData(String row_id, String item_name, String price, String quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,item_name);
        cv.put(COLUMN_PRICE,price);
        cv.put(COLUMN_QUANTITY,quantity);

        long result = db.update(TABLE_NAME,cv,"id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"Failed to Update Grocery Item",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successfully Updated",Toast.LENGTH_SHORT).show();
        }
    }

    //delete grocery item method
    void deleteGroceryItem(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"Failed to Delete Grocery Item",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successfully Deleted",Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.a4flicksapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHandlerDailyTasks extends SQLiteOpenHelper {
    //methods which we use to coordinate with the DB are implemented inside this class(this is like a model class )

    //define the parameter variables used in the constructor and initialize them with static values
    private static final int VERSION = 1; //if we do some changes in a DB table, that will be considered as a version update of the DB. So if we change anything like that, DB and the tables will be recreated
    private static final String DB_NAME = "BestPlanDatabase";
    private static final String TABLE_NAME = "DailyTasks";

    //variables for table column names
    private static final String ID = "id";
    private static final String TIME = "time";
    private static final String DESCRIPTION = "description";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    //constructor
    public DbHandlerDailyTasks(@Nullable Context context) {
        super(context, DB_NAME , null, VERSION); //calling the constructor of the SQLiteOpenHelper super class to create the DB
        //since this isn't an activity java class but a normal java class, this don't get a context in general.
        // therefor context is a parameter that we have to give definitely everytime when we creating objects from this class
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //tables will be created inside this class

        /*CREATE TABLE dailyTasks(id INTEGER PRIMARY KEY AUTOINCREMENT, time TEXT, description TEXT, started TEXT, finished TEXT);*/
        String TABLE_CREATE_QUERY =  "CREATE TABLE " + TABLE_NAME+" " +
                "("
                    +ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +TIME +" TEXT,"
                    +DESCRIPTION +" TEXT,"
                    +STARTED +" TEXT,"
                    +FINISHED +" TEXT" +
                ");";

        //now run the table query
        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Here, IF any update/change happens, a new table will be created in the DB after deleting the existing table
        //there for, ex: if we change the version no from 1 to 2, then this method will be run and a new table will be recreated

            //since this method was called, drop the existing table
            String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;
            db.execSQL(DROP_TABLE_QUERY);
            //now create that table again
            onCreate(db);


    }

    //QUESTIONS
    //everytime when we create a new object of this handler class, after running the constructor, does it create a new table during onCreate execution always?
        //NO, a new table will be created only if there is'nt a table from that name. unless it will not
    //do i need to change the VERSION no everytime when i do a change in the table structure
        //Yes, then u have to increment the VERSION no by 1
        //then, when this class runs, it will first run the onUpgrade method(there, the existing table will be deleted. and a new table will be recreated with the latest changes)
}

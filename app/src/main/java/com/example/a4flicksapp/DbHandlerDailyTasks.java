package com.example.a4flicksapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    //create a method to insert user input data to DB(since this method will be accessed by the model class, make it as a public method)
    public void insertDailyTask(DailyTaskModel modelObj){
        //create a new database writable object to write data into db
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        //create a contentValues object to structure data and put in the db
        ContentValues contentValues = new ContentValues();
            //now put the user input data into the ContentValues obj by calling the get methods in model class
            contentValues.put(TIME, modelObj.getTime());
            contentValues.put(DESCRIPTION,modelObj.getDescription()); //since id is autoincrementing, it is not needed to send by us. ** put(key,data) here key is the column name**
            contentValues.put(STARTED, modelObj.getStarted());
            contentValues.put(FINISHED, modelObj.getFinished());
        //now save these values to table
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();//since in our project there are 4 db connections, it's good to close each connection when they are done to avoid conflicts
    }

    public int countNoOfTasks(){
        SQLiteDatabase db = getReadableDatabase();
        String countQuery = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(countQuery, null); //all the rows related to the query will be stored in this cursor obj
        return cursor.getCount(); //getCount method of cursor class, returns the no of rows in the cursor
    }


    //QUESTIONS
    //everytime when we create a new object of this handler class, after running the constructor, does it create a new table during onCreate execution always?
        //NO, a new table will be created only if there is'nt a table from that name. unless it will not
    //do i need to change the VERSION no everytime when i do a change in the table structure
        //Yes, then u have to increment the VERSION no by 1
        //then, when this class runs, it will first run the onUpgrade method(there, the existing table will be deleted. and a new table will be recreated with the latest changes)
}

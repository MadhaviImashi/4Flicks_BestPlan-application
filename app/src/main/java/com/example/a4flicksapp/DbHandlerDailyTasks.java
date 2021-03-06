package com.example.a4flicksapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
    Context context;

    //constructor
    public DbHandlerDailyTasks(@Nullable Context context) {
        super(context, DB_NAME , null, VERSION); //calling the constructor of the SQLiteOpenHelper super class to create the DB
        this.context = context;
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
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "successfully added", Toast.LENGTH_LONG).show();

        sqLiteDatabase.close();//since in our project there are 4 db connections, it's good to close each connection when they are done to avoid conflicts
    }

    public int countNoOfTasks(){
        SQLiteDatabase db = getReadableDatabase();
        String countQuery = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(countQuery, null); //all the rows related to the query will be stored in this cursor obj
        return cursor.getCount(); //getCount method of cursor class, returns the no of rows in the cursor
    }

    public List<DailyTaskModel> getAllTasks(){ //this getAllTAsks() method will return an arrayList. that arrayList contains, our model class type objects(for each row in the table)

        List<DailyTaskModel> modleObjOfTASKS = new ArrayList(); //a new ArrayList is created using our model class type list objects

        SQLiteDatabase db = getReadableDatabase();
        String retrievQuery = "SELECT * FROM " + TABLE_NAME;

        Cursor cursorObj = db.rawQuery(retrievQuery, null);

        //values can be stored in this arrayList only if there are data in the DB //so we should first check the availabilty of data in the table
        if(cursorObj.moveToFirst()){ //this will point to the first raw in the cursor obj //if there's no data, this will return FALSE
            do{
                //create new model object
                DailyTaskModel taskModelObj = new DailyTaskModel();
                //set values retrieved from the DB to this obj (using the cursorObj)
                taskModelObj.setId(cursorObj.getInt(0)); //value in the 1st column of the table
                taskModelObj.setTime(cursorObj.getString(1)); //value in the 2nd column
                taskModelObj.setDescription(cursorObj.getString(2));
                taskModelObj.setStarted(cursorObj.getLong(3));
                taskModelObj.setFinished(cursorObj.getLong(4));

                //now add this modelObj in to model type arraylist which we created at the begining
                modleObjOfTASKS.add(taskModelObj); //this will create an arraylist

            }while( cursorObj.moveToNext());//until the last row of the table, cursorObj will move one by one
        }
        //the loop will terminate when data in all the rows in the cursorObj have read and set to the new task model obj and ARRAYLIST will be created with those objects
        return modleObjOfTASKS; //this arraylist will be returned
    }

    public void updateTask(DailyTaskModel modelObj){
        SQLiteDatabase db = getWritableDatabase();

        //new value for one column
        ContentValues cv = new ContentValues();
        cv.put(TIME, modelObj.getTime());
        cv.put(DESCRIPTION, modelObj.getDescription());
        cv.put(STARTED, modelObj.getStarted());
        cv.put(FINISHED, modelObj.getFinished());

        long result = db.update(TABLE_NAME, cv,ID +" =?", new String[]{String.valueOf(modelObj.getId())});
        if (result == -1){
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "successfully Updated", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void deleteTask(int id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, ID +" =?", new String[]{String.valueOf(id)}); //use the delete() to delete
        db.close();
    }


    //QUESTIONS
    //everytime when we create a new object of this handler class, after running the constructor, does it create a new table during onCreate execution always?
        //NO, a new table will be created only if there is'nt a table from that name. unless it will not
    //do i need to change the VERSION no everytime when i do a change in the table structure
        //Yes, then u have to increment the VERSION no by 1
        //then, when this class runs, it will first run the onUpgrade method(there, the existing table will be deleted. and a new table will be recreated with the latest changes)
}

package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ToDoList extends AppCompatActivity {

    private FloatingActionButton btnAddNewTask;
    private ListView listView;
    private TextView count;
    private ImageButton btnHomeOfList;
    Context context;//a context object
    private List<DailyTaskModel> dailyTasksList; //declare a model class type List variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        //initialize the object vaiables
        btnAddNewTask = findViewById(R.id.floatingActionButton);
        btnHomeOfList = findViewById(R.id.btnHomeOfList);
        listView = findViewById(R.id.tasksList); //this listView will be needed to set the Adapter
        count = findViewById(R.id.displayCount);
        context = this;
        dailyTasksList = new ArrayList<>(); //now a memory will be allocated to this dailyTasks variable

        //create a db handler obj to access count method of it
        DbHandlerDailyTasks handlerObj = new DbHandlerDailyTasks(context);

        String showCount = handlerObj.countNoOfTasks() + " Tasks to do"; //call the count method
        count.setText(showCount);

        //call the data retrieving method in dbHandler class and assign the arraylist in a list type variable
        dailyTasksList = handlerObj.getAllTasks();

        ToDoAdapter adapter = new ToDoAdapter(context, R.layout.single_task, dailyTasksList ); //pass the single_task layout and arraylist
        //now display the list of all rows on our LIST VIEW by calling the getView method in a loop
        listView.setAdapter(adapter);

        btnAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAddNewTaskActivity(v);
            }
        });

        btnHomeOfList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayHomePage(v);
            }
        });

        //implement the dialog box popup
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get the task object which was selected by the user from the arrayList by calling the get() method
                //and save that task in a model type obj
                DailyTaskModel taskObj = dailyTasksList.get(position);
                //now by calling the get() methods of model class, u can access any column data belongs to this particular task object
                String time =  taskObj.getTime();
                String desc =  taskObj.getDescription();
                String tvTime = "\t\t\t\t\t\tTime - " + time;
                String tvDesc = "\t\t\t\t\t\t" + desc;

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setTitle(tvTime);
                alertBuilder.setMessage(tvDesc);

                alertBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handlerObj.deleteTask(taskObj.getId());//call the deleteTask() to delete this taskObj
                        //redirrect the user to the same activity
                        Intent i = new Intent(context, ToDoList.class);
                        startActivity(i);
                    }
                });
                alertBuilder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(context, UpdateTask.class);
                        i.putExtra("Time", time);
                        i.putExtra("Description", desc);
                        startActivity(i);
                    }
                });
                alertBuilder.setNeutralButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(context, ToDoList.class);
                        startActivity(i);
                    }
                });
                alertBuilder.show();
            }
        });
    }

    public void displayAddNewTaskActivity(View view){
        Intent intent = new Intent(this, AddNewTask.class);
        startActivity(intent);
    }

    public void displayHomePage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
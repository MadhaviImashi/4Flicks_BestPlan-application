package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
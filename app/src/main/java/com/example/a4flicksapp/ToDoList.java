package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ToDoList extends AppCompatActivity {

    private FloatingActionButton btnAddNewTask;
    private ListView listView;
    private TextView count;

    Context context;//a context object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        //initialize the object vaiables
        btnAddNewTask = findViewById(R.id.floatingActionButton);
        listView = findViewById(R.id.tasksList); //this listView will be needed to set the Adapter
        count = findViewById(R.id.displayCount);
        context = this;

        //create a db handler obj to access count method of it
        DbHandlerDailyTasks handlerObj = new DbHandlerDailyTasks(context);

        String showCount = handlerObj.countNoOfTasks() + " Tasks to do";
        count.setText(showCount);

        btnAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAddNewTaskActivity(v);
            }
        });
    }

    public void displayAddNewTaskActivity(View view){
        Intent intent = new Intent(this, AddNewTask.class);
        startActivity(intent);
    }
}
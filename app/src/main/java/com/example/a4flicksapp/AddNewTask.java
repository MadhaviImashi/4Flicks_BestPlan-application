package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddNewTask extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    String msg;

    private Button btnAddTask;
    private EditText  inputDesc;
    private TextView displayTime;
    String timeInHours, timeInMins;
    private Context context;
    //to access the insert() method in DbHandler class
    private DbHandlerDailyTasks dbHandlerObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);

        btnAddTask = findViewById(R.id.btnAddTask);
        inputDesc = findViewById(R.id.editTextDescription);
        context = this;

        dbHandlerObj = new DbHandlerDailyTasks(context); //call the dbHandler class constructor to create the handler type obj to access insert method in that class
        //DbHandlerDailyTasks dbHandlerObj = new DbHandlerDailyTasks(context);

        //implement the clickListener event to insert data
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callToDoListActvity(v);
            }
        });
    }

    //implement the methods related to timePicker dialog class
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        displayTime = (TextView) findViewById(R.id.tvTime);
        displayTime.setText("Hour: " + hourOfDay + " Minute: " + minute);
        timeInHours = String.valueOf(hourOfDay);
        timeInMins = String.valueOf(minute);

        msg = "You have set the time as : " + hourOfDay + ":" + minute + "to complete the Task";
        Toast.makeText(this, "Hello "+ msg, Toast.LENGTH_LONG).show();
    }

    public void callToDoListActvity(View view){
        //create the variables to store user input data
        String userDesc = inputDesc.getText().toString();
        String userTime = timeInHours + "."+ timeInMins;
        long startedTime = System.currentTimeMillis(); //the time when the user tries to add this particular task

        //now create an object of the Model class 'DailyTaskModel' to send these data to it first as a model class type object
        DailyTaskModel modelObj = new DailyTaskModel(userTime, userDesc, startedTime, 0);

        //now call the insert() method in DBHandler class by passing the modelObj into it
        dbHandlerObj.insertDailyTask(modelObj);
    }

}
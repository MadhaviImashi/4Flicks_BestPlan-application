package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddNewTask extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView time = (TextView) findViewById(R.id.tvTime);
        time.setText("Hour: " + hourOfDay + " Minute: " + minute);

        msg = "You have set the time as: " + hourOfDay + ":" + minute + "to complete the Task";
        Toast.makeText(this, "Hello "+ msg, Toast.LENGTH_LONG).show();
    }

}
package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class bmiCalculator extends AppCompatActivity {

    Button btnFragmentMainNav, calculateBMI;
    EditText inputH, inputW, inputAge;
    TextView BMIresult;

    int height, weight;
    double hInMeters;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        btnFragmentMainNav = findViewById(R.id.btnFragmentMainNav);
        calculateBMI = findViewById(R.id.calculateBMI);
        BMIresult = findViewById(R.id.tvBMIresult);
        inputH = findViewById(R.id.h);
        inputW = findViewById(R.id.w);
        inputAge = findViewById(R.id.age);

        //----------------------------------------
        //radioGroup = findViewById(R.id.radioGroup);
        //Button buttonApply = findViewById(R.id.button_apply);

        //display the customized Tost message at the loading of this activity
        //creating the layoutInflater instance
        LayoutInflater li = getLayoutInflater();
        //getting the view object as defined in the customtoast.xml file
        View layout = li.inflate(R.layout.custom_toast, (ViewGroup)
                findViewById(R.id.custom_toast_layout));

        //creating the Toast object
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0,0);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();

        btnFragmentMainNav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayFragmentsMain(v);
            }
        });

        calculateBMI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                calculateBMI(v);
            }
        });
    }

    public void calculateBMI(View view){

        String h = inputH.getText().toString();
        String w = inputW.getText().toString();
        String age = inputAge.getText().toString();

        //convert the user input text type data into number format
        height = Integer.parseInt(h);
        hInMeters = height / 100.0; //convert height into meters
        weight = Integer.parseInt(w);

        //calculate BMI value for according to the user input data
        double BMI = weight / hInMeters;

        //classify the weight category according to the BMI range
        if(BMI < 18.5){
            category = "Underweight";
        }
        else if(BMI < 24.9){
            category = "Normal Weight";
        }
        else if(BMI < 39.9){
            category = "Overweight";
        }
        else
            category = "Morbidly obese";

        //display the weight category of the user
        BMIresult.setText("You are in " + category);

    }

    public void displayFragmentsMain(View view){

        Intent intent = new Intent(this, FragmentedTips.class);
        startActivity(intent);
    }
}
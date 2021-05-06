package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.number.Precision;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class bmiCalculator extends AppCompatActivity {

    Button btnFragmentMainNav, calculateBMI;
    ImageView btnHome;
    EditText inputH, inputW, inputAge;
    TextView BMIresult;

    int height, weight;
    double hInMeters;
    private double BMI;
    private String BMIval;
    private double BMIvalue;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        btnFragmentMainNav = findViewById(R.id.btnFragmentMainNav);
        calculateBMI = findViewById(R.id.calculateBMI);
        btnHome = findViewById(R.id.homeButton);
        BMIresult = findViewById(R.id.tvBMIresult);
        inputH = findViewById(R.id.h);
        inputW = findViewById(R.id.w);
        inputAge = findViewById(R.id.age);

        btnFragmentMainNav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayFragmentsMain(v);
            }
        });

        calculateBMI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //first assign the values of BMI and CATEGORY by calling the 2 methods
                double BMIvalue = calculateBMI(v);
                String category = getWeightCategory(BMIvalue);
                //display the weight category of the user in the TextView
                if(BMIvalue == 0){
                    BMIresult.setText("RESULT");
                }
                else
                    BMIresult.setText("  Your BMI: " + BMIvalue + "\n\n" + category);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayHomePage(v);
            }
        });
    }

    public double  calculateBMI(View view) {

        String h = inputH.getText().toString();
        String w = inputW.getText().toString();
        String age = inputAge.getText().toString();

        //validate the input fields
        if (h.length() == 0 && w.length() == 0) {
            Toast.makeText(this, "Please enter Height & Weight", Toast.LENGTH_LONG).show();
            return 0;
        } else if (h.length() == 0) {
            Toast.makeText(this, "Please Enter height", Toast.LENGTH_LONG).show();
            return 0;
        } else if (w.length() == 0) {
            Toast.makeText(this, "Please Enter Weight", Toast.LENGTH_LONG).show();
            return 0;
        } else {
            //convert the user input text type data into number format
            height = Integer.parseInt(h);
            hInMeters = height / 100.0; //convert height into meters
            weight = Integer.parseInt(w);

            //calculate BMI value for according to the user input data
            this.BMI = weight / hInMeters;
            this.BMIval = String.format("%.2f", BMI); //round of the BMI value to 2 decimal places
            this.BMIvalue = Double.valueOf(BMIval);

            return BMIvalue;
        }
    }

    public String getWeightCategory(double BMIvalue){

            //classify the weight category according to the BMI range
            if(BMIvalue == 0){
                category = "";
            } else if(BMIvalue < 18.5) {
                category = "Underweight";
            } else if (BMIvalue < 24.9) {
                category = "Normal Weight";
            } else if (BMIvalue < 39.9) {
                category = "Overweight";
            } else
                category = "Morbidly obese";

            return category;
    }


    public void displayFragmentsMain(View view){

        Intent intent = new Intent(this, FragmentedTips.class);
        startActivity(intent);
    }

    public void displayHomePage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
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

    int weight;
    double height, hInMeters;
    private float BMI;
    private String BMIval;
    private float BMIvalue;
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

                //call the 2 classes to get the user input values to h and w
                String h = takeUserInputHeight(v);
                String w = takeUserInputWeight(v);
                //now cal the bmi using h and w and find category
                float BMIvalue = calculateBmi(h, w);
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

    public String takeUserInputHeight(View view){
        String height = inputH.getText().toString();
        return height;
    }

    public String takeUserInputWeight(View view){
        String weight = inputW.getText().toString();
        return weight;
    }

    public float  calculateBmi(String inputHeight, String inputWeight) {

        String h = inputHeight;
        String w = inputWeight;
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
            height = Float.valueOf(h);
            hInMeters = (float) ((float)height / 100.0); //convert height into meters
            weight = Integer.parseInt(w);

            //calculate BMI value for according to the user input data
            this.BMI = (float) ((float)weight / hInMeters);
            this.BMIval = String.format("%.1f", BMI); //round of the BMI value to 2 decimal places
            this.BMIvalue = Float.parseFloat(BMIval);

            return BMIvalue;
        }
    }

    public String getWeightCategory(float BMIvalue){

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

    //another simple bmi calculator method
    public float calculateBMI(int weight, float height){

        float hInMeters = (float) ((float)height / 100.0);
        float bmi = weight / hInMeters;
        String bmiString = String.format("%.1f", bmi);
        float BMI = Float.parseFloat(bmiString);
        return BMI;
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
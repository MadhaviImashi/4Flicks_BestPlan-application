package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class calorCalcu extends AppCompatActivity {
    EditText calbr,callu,caldin;
    TextView calres;
    String calcalculation, calresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calor_calcu);

        calbr=findViewById(R.id.calBre);
        callu=findViewById(R.id.calLun);
        caldin=findViewById(R.id.calDin);
        calres=findViewById(R.id.calResult);

    }

    public void calorieCalcu(View view) {
        String br1 = calbr.getText().toString();
        String lu1 = callu.getText().toString();
        String din1 = caldin.getText().toString();
        if (TextUtils.isEmpty(calbr.getText())) {
            calbr.setError("Enter valid input");

        }
        if (TextUtils.isEmpty(callu.getText())) {
            callu.setError("Enter valid input");

        }
        if (TextUtils.isEmpty(caldin.getText())) {
            caldin.setError("Enter valid input");
            caldin.requestFocus();
        } else {

            float calvalues = Float.parseFloat(br1);
            float calvalues2 = Float.parseFloat(lu1);
            float calvalues3 = Float.parseFloat(din1);

            float adds = addcal(calvalues, calvalues2, calvalues3);
            float calintake = adds;


            if (calintake < 2000) {
                calresult = "Your Calorie Intake Should Be Higher";

            } else if (calintake > 2700) {
                calresult = "Your Calorie Intake Should Be Lower";

            } else if (calintake >= 2000 && calintake <= 2700) {
                calresult = "Your Calorie Intake Is Perfect";
            }
            calcalculation = "Calorie Intake \n" + calintake + "\n" + calresult;
            calres.setText(calcalculation);


        }
    }

    protected float addcal(float x1,float x2,float x3){ return  x1+x2+x3;}
}

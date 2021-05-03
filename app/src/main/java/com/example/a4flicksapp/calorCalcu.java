package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    public void calorieCalcu(View view){
        String br1=calbr.getText().toString();
        String lu1=callu.getText().toString();
        String din1=caldin.getText().toString();

        float calvalues=Float.parseFloat(br1);
        float calvalues2=Float.parseFloat(lu1);
        float calvalues3=Float.parseFloat(din1);

        float calintake= calvalues+calvalues2+calvalues3;

        if(calintake<2000){
            calresult="Your Calorie Intake Should Be Higher";

        }else if (calintake>2700){
            calresult="Your Calorie Intake Should Be Lower";

        }else if (calintake>=2000 && calintake<=2700){
            calresult="Your Calorie Intake Is Perfect";
        }
        calcalculation="Calorie Intake \n" +calintake+ "\n"+calresult;
        calres.setText(calcalculation);


    }
}
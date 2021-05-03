package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editMeal extends AppCompatActivity {

    private EditText day3, break3,lunch3,dinner3;
    private Button editm;
    private DBhandlerMeal dbHandler;
    private Context context;
    private Long updateDate1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);
        context=this;
        dbHandler = new DBhandlerMeal(context);

        day3= findViewById(R.id.editday);
        break3= findViewById(R.id.editbr);
        lunch3= findViewById(R.id.editlu);
        dinner3= findViewById(R.id.editdin);
        editm=findViewById(R.id.editdmeal);
        final String id1=getIntent().getStringExtra("id1");
       mealPlan meal7= dbHandler.getSingleMeal(Integer.parseInt(id1));
        day3.setText(meal7.getDay1());
        break3.setText(meal7.getBreakfirst1());
        lunch3.setText(meal7.getLunch1());
        dinner3.setText(meal7.getDinner1());

        editm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usday=day3.getText().toString();
                String usbr=break3.getText().toString();
                String uslu=lunch3.getText().toString();
                String usdin=dinner3.getText().toString();
                updateDate1=System.currentTimeMillis();

                mealPlan meal8= new mealPlan(Integer.parseInt(id1),usday,usbr,uslu,usdin,updateDate1,0);
                int state = dbHandler.updateSingleMeal(meal8);
                System.out.println(state);
                startActivity(new Intent(context,listOfMeals.class));

            }
        });

    }
}
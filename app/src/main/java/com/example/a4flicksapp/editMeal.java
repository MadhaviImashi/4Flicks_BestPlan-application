package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                //validate if all the required inputs are given

                if (TextUtils.isEmpty(day3.getText())) {
                    day3.setError("Enter Day");
                    day3.requestFocus();
                }
                else if (TextUtils.isEmpty(break3.getText())) {
                    break3.setError("Enter Breakfast");
                    break3.requestFocus();
                }
               else if (TextUtils.isEmpty(lunch3.getText())) {
                    lunch3.setError("Enter Lunch");
                    lunch3.requestFocus();
                }
               else if (TextUtils.isEmpty(dinner3.getText())) {
                    dinner3.setError("Enter Dinner");
                    dinner3.requestFocus();
                }
                else {
                    String usday = day3.getText().toString();
                    String usbr = break3.getText().toString();
                    String uslu = lunch3.getText().toString();
                    String usdin = dinner3.getText().toString();
                    updateDate1 = System.currentTimeMillis();

                    mealPlan meal8 = new mealPlan(Integer.parseInt(id1), usday, usbr, uslu, usdin, updateDate1, 0);
                    int state = dbHandler.updateSingleMeal(meal8);
                    System.out.println(state);
                    Toast.makeText(editMeal.this, "Meal Plan Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context, listOfMeals.class));

                }
            }
        });

    }
}
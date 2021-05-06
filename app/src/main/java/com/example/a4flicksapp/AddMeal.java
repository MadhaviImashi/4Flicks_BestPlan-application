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

public class AddMeal extends AppCompatActivity {

    private EditText day1,lunch1,breakfirst1,dinner1;
    private Button add1;
    private DBhandlerMeal dbHandler;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        day1=findViewById(R.id.enterday);
        breakfirst1=findViewById(R.id.enterbr);
        lunch1=findViewById(R.id.enterlu);
        dinner1=findViewById(R.id.enterdin);
        add1=findViewById(R.id.addmeal);
        context=this;
        dbHandler= new DBhandlerMeal(context);

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate if all the required inputs are given
                if (TextUtils.isEmpty(day1.getText())) {
                    day1.setError("Enter Day");
                    day1.requestFocus();
                }
                if (TextUtils.isEmpty(breakfirst1.getText())) {
                        breakfirst1.setError("Enter Breakfast");
                        breakfirst1.requestFocus();
                    }
                if (TextUtils.isEmpty(lunch1.getText())) {
                    lunch1.setError("Enter Lunch");
                    lunch1.requestFocus();
                }
                if (TextUtils.isEmpty(dinner1.getText())) {
                    dinner1.setError("Enter Dinner");
                    dinner1.requestFocus();
                }
                else {
                    String userday1 = day1.getText().toString();
                    String userbreakfirst1 = breakfirst1.getText().toString();
                    String userlunch1 = lunch1.getText().toString();
                    String userdinner1 = dinner1.getText().toString();
                    long started = System.currentTimeMillis();

                    mealPlan meals1 = new mealPlan(userday1, userbreakfirst1, userlunch1, userdinner1, started, 0);
                    dbHandler.addMealplan(meals1);
                    Toast.makeText(AddMeal.this, "Meal Plan Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context, listOfMeals.class));


                }
            }
        });
    }
}
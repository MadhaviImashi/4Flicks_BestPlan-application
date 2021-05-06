package com.example.a4flicksapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class listOfMeals extends AppCompatActivity {

    private Button add1;
    private ListView listView1;
    private TextView count1;
    Context context;
    private DBhandlerMeal dbHandler;
    private List<mealPlan> meals4;
    private ImageButton homesm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_meals);
        context = this;

        dbHandler = new DBhandlerMeal(context);
        add1= findViewById(R.id.MealAdd);
        listView1=findViewById(R.id.MealList);
        count1=findViewById(R.id.MealCount);
        meals4= new ArrayList<>();
        meals4= dbHandler.getAllMeals();
        homesm2= findViewById(R.id.mlplhome2);

        mealAdapter adapter= new mealAdapter(context,R.layout.singlemeal,meals4);
        listView1.setAdapter(adapter);

        int countmeals1 = dbHandler.countmeals();
        count1.setText("You have "+countmeals1+" meals");

        homesm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,MainActivity.class));
            }
        });

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,AddMeal.class));
            }
        });


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mealPlan meal5= meals4.get(position);
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle(meal5.getDay1());
                builder.setMessage(meal5.getBreakfirst1());
                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        meal5.setFinished(System.currentTimeMillis());
                        dbHandler.updateSingleMeal(meal5);
                        startActivity(new Intent(context,listOfMeals.class));

                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteMeal(meal5.getId1());
                        Toast.makeText(listOfMeals.this, "Meal Plan Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context,listOfMeals.class));

                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,editMeal.class);
                        intent.putExtra("id1",String.valueOf(meal5.getId1()));
                        startActivity(intent);

                    }
                });
                builder.show();
            }
        });

    }
}
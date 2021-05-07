package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnNavBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNavBMI = findViewById(R.id.btnNavBMI);

        //----------------------------------------
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
    }

    public void displayBMIactivity(View view){

        Intent intent = new Intent(this, bmiCalculator.class);

        startActivity(intent);
    }

    public void displayAllRecipesWithRecipes(View view){
        Intent intent = new Intent(this, all_recipes_with_recipes.class);

        startActivity(intent);
    }
}
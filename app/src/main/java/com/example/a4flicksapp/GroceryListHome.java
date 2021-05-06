package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GroceryListHome extends AppCompatActivity {
    FloatingActionButton next_page_btn;
    ImageView home_image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list_home);

        next_page_btn=findViewById(R.id.btnnextpage);
        home_image_view=findViewById(R.id.TbHomeBtn);

        //create onclickListener method to navigate to my grocery list when click on floating action button
        next_page_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroceryListHome.this,GroceryListItemList.class);
                startActivity(intent);
            }
        });

        //create onclickListener method to navigate to home page when click on home icon
        home_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroceryListHome.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //create onclick method to navigate to my grocery list when click on text view
    public void displayGroceryListItemList(View view) {
        Intent intent = new Intent(this, GroceryListItemList.class);
        startActivity(intent);
    }

}
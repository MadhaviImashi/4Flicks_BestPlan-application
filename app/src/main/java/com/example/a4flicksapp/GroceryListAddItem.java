package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GroceryListAddItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list_add_item);
    }

    //create onclick method to navigate to your grocery list
    public void displayGroceryList(View view) {
        Intent intent = new Intent(this, GroceryListItemList.class);
        startActivity(intent);
    }

}
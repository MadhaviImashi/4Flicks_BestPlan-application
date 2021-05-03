package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GroceryListItemList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list_item_list);
    }

    //create onclick method to navigate to add item page
    public void displayGroceryListAddItem(View view) {
        Intent intent = new Intent(this, GroceryListAddItem.class);
        startActivity(intent);
    }

    //create onclick method to navigate to grocery list home page
    public void displayGroceryListHome(View view) {
        Intent intent = new Intent(this, GroceryListHome.class);
        startActivity(intent);
    }
}
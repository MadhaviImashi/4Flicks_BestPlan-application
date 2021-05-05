package com.example.a4flicksapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GroceryListItemList extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHandlerGrocery myDB;
    ArrayList<String> item_id, item_name, item_price, quantity;
    GroceryAdapter customAdapter;
    TextView textView;
    float totalAmount = 0;

    //get grocery list using recycler view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list_item_list);

        recyclerView=findViewById(R.id.recyclerViewGrocery);

        myDB = new DBHandlerGrocery(GroceryListItemList.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_price = new ArrayList<>();
        quantity = new ArrayList<>();

        StoreData();

        customAdapter = new GroceryAdapter(GroceryListItemList.this,GroceryListItemList.this,item_id,item_name,item_price,quantity);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager((new LinearLayoutManager(GroceryListItemList.this)));
        textView = (TextView)findViewById(R.id.groceryTotalAmount);
        totalAmount = getTotal();
        String totString = String.format("%.2f",totalAmount);
        textView.setText(String.valueOf(totString));
    }

    @Override //update method (after update the data redirect to grocery list view with updated data)
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    //create onclick method to navigate to grocery list add item view
    public void displayGroceryListAddItem(View view) {
        Intent intent = new Intent(this, GroceryListAddItem.class);
        startActivity(intent);
    }

    //create onclick method to navigate to grocery list home view
    public void displayGroceryListHome(View view) {
        Intent intent = new Intent(this, GroceryListHome.class);
        startActivity(intent);
    }

    void StoreData(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(1));
                item_price.add(cursor.getString(2));
                quantity.add(cursor.getString(3));
            }
        }
    }

    //get total amount of the grocery list
    public float getTotal() {
        float totalAmount = 0 ;
        float itemPrice = 0;
        String price = "0",qut="0";
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                price = cursor.getString(cursor.getColumnIndex("price"));
                qut = cursor.getString(cursor.getColumnIndex("quantity"));
                itemPrice = Float.parseFloat(price) * Float.parseFloat(qut);
                totalAmount = totalAmount + itemPrice;
            }
        }
        return totalAmount;
    }
}
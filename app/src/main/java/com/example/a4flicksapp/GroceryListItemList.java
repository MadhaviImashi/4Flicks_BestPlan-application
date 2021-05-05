package com.example.a4flicksapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GroceryListItemList extends AppCompatActivity {
    RecyclerView recyclerView;
    DBHandlerGrocery myDB;
    ArrayList<String> item_id, item_name, item_price, quantity;
    GroceryAdapter customAdapter;
    TextView total_amount_textView,empty_text,total_view;
    ImageView empty_image,delete_all_items;
    FloatingActionButton add_item_float_btn;
    float totalAmount = 0;

    //get grocery list using recycler view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list_item_list);

        recyclerView=findViewById(R.id.recyclerViewGrocery);
        empty_image=findViewById(R.id.no_data_image);
        empty_text=findViewById(R.id.no_data_text);
        total_amount_textView=findViewById(R.id.groceryTotalAmount);
        total_view=findViewById(R.id.groceryTotalView);
        add_item_float_btn=findViewById(R.id.groceryGoToAddItem);
        delete_all_items=findViewById(R.id.delete_all_items);

        myDB = new DBHandlerGrocery(GroceryListItemList.this);
        item_id = new ArrayList<>();
        item_name = new ArrayList<>();
        item_price = new ArrayList<>();
        quantity = new ArrayList<>();

        StoreData();

        customAdapter = new GroceryAdapter(GroceryListItemList.this,GroceryListItemList.this,item_id,item_name,item_price,quantity);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager((new LinearLayoutManager(GroceryListItemList.this)));

        //call getTotal method to get total amount of grocery list and set into textView
        totalAmount = getTotal();
        String totString = String.format("%.2f",totalAmount);
        total_amount_textView.setText(String.valueOf(totString));

        //create onClickListener method to navigate Add item View
        add_item_float_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroceryListItemList.this,GroceryListAddItem.class);
                startActivity(intent);
            }
        });

        //create onClickListener method call deleteAllGroceryItems method
        delete_all_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAllDialogBox();
            }
        });
    }

    @Override //update method (after update the data redirect to grocery list view with updated data)
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    //create onclick method to navigate to grocery list home view
    public void displayGroceryListHome(View view) {
        Intent intent = new Intent(this, GroceryListHome.class);
        startActivity(intent);
    }

    void StoreData(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_image.setVisibility(View.VISIBLE);
            empty_text.setVisibility(View.VISIBLE);
            total_amount_textView.setVisibility(View.GONE);
            total_view.setVisibility(View.GONE);
        }
        else{
            while(cursor.moveToNext()){
                item_id.add(cursor.getString(0));
                item_name.add(cursor.getString(1));
                item_price.add(cursor.getString(2));
                quantity.add(cursor.getString(3));
            }
            empty_image.setVisibility(View.GONE);
            empty_text.setVisibility(View.GONE);
            total_amount_textView.setVisibility(View.VISIBLE);
            total_view.setVisibility(View.VISIBLE);
        }
    }

    //get total amount of the grocery list
    public float getTotal() {
        float totalAmount = 0 ;
        float itemPrice = 0;
        String price = "0",qut="0";
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() != 0) {
            while(cursor.moveToNext()) {
                price = cursor.getString(cursor.getColumnIndex("price"));
                qut = cursor.getString(cursor.getColumnIndex("quantity"));
                itemPrice = Float.parseFloat(price) * Float.parseFloat(qut);
                totalAmount = totalAmount + itemPrice;
            }
        }
        return totalAmount;
    }

    //create dialog box to confirm all the item would be deleted or not
    // if YES call deleteAllGroceryItems method in DBHandlerGrocery and recreate grocery list view
    void DeleteAllDialogBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(GroceryListItemList.this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all the grocery items?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHandlerGrocery myDB = new DBHandlerGrocery(GroceryListItemList.this);
                myDB.deleteAllGroceryItems(); //if YES delete selected grocery item in database table
                recreate();//recreate the grocery list view
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if NO don't do anything
            }
        });
        builder.create().show();
    }
}
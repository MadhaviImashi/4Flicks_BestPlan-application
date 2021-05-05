package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GroceryListUpdateItem extends AppCompatActivity {
    EditText item_name_input,item_price_input,item_quantity_input;
    String id,item_name,item_price,item_quantity;
    Button edit_button;
    Button delete_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list_update_item);

        item_name_input=findViewById(R.id.textInputItemName2);
        item_price_input=findViewById(R.id.textInputPrice2);
        item_quantity_input=findViewById(R.id.textInputQuantity2);

        //First get data and set data
        getAndSetIntentData();

        //create onclickListener method to call updateItemData method and navigate to view grocery list UI
        edit_button=findViewById(R.id.groceryEditBtn);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //after call getAndSetIntentData update database
                DBHandlerGrocery myDB = new DBHandlerGrocery(GroceryListUpdateItem.this);
                item_name = item_name_input.getText().toString().trim();
                item_price = item_price_input.getText().toString().trim();
                item_quantity = item_quantity_input.getText().toString().trim();
                myDB.updateItemData(id,item_name,item_price,item_quantity);

                Intent intent = new Intent(GroceryListUpdateItem.this, GroceryListItemList.class);
                startActivity(intent);
            }
        });

        //create onclickListener method to call deleteGroceryItem method in DBHandlerGrocery and navigate to grocery list View
        delete_button=findViewById(R.id.groceryDeleteBtn);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandlerGrocery myDB = new DBHandlerGrocery(GroceryListUpdateItem.this);
                myDB.deleteGroceryItem(id);
            }
        });
    }

    //navigate to the grocery list view
    public void displayGroceryList(View view){
        Intent intent = new Intent(this,GroceryListItemList.class);
        startActivity(intent);
    }

    //get stored data and display data in update item view
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("item_name") &&
                getIntent().hasExtra("price") && getIntent().hasExtra("quantity")){
            //getting Data from Intent
            id = getIntent().getStringExtra("id");
            item_name = getIntent().getStringExtra("item_name");
            item_price = getIntent().getStringExtra("price");
            item_quantity = getIntent().getStringExtra("quantity");

            //setting Intent Data in Grocery List Update Item view
            item_name_input.setText(item_name);
            item_price_input.setText(item_price);
            item_quantity_input.setText(item_quantity);

        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }
}
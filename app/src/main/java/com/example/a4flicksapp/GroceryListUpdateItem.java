package com.example.a4flicksapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
                //validate item name text view is not empty
                if(TextUtils.isEmpty(item_name_input.getText().toString())){
                    item_name_input.setError("Item Name can not be empty");
                    item_name_input.requestFocus();
                }
                //validate item price text view is not empty
                else if(TextUtils.isEmpty(item_price_input.getText().toString())){
                    item_price_input.setError("Price can not be empty");
                    item_price_input.requestFocus();
                }
                //validate item quantity text view is not empty
                else if(TextUtils.isEmpty(item_quantity_input.getText().toString())){
                    item_quantity_input.setError("Quantity can not be empty");
                    item_quantity_input.requestFocus();
                }else {
                    //after call getAndSetIntentData update database
                    DBHandlerGrocery myDB = new DBHandlerGrocery(GroceryListUpdateItem.this);
                    item_name = item_name_input.getText().toString().trim();
                    item_price = item_price_input.getText().toString().trim();
                    item_quantity = item_quantity_input.getText().toString().trim();
                    myDB.updateItemData(id, item_name, item_price, item_quantity);

                    Intent intent = new Intent(GroceryListUpdateItem.this, GroceryListItemList.class);
                    startActivity(intent);
                }
            }
        });

        //create onclickListener method to call confirmDialogBox method
        delete_button=findViewById(R.id.groceryDeleteBtn);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialogBox();
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

    //create dialog box to confirm item would be deleted or not
    // if YES call deleteGroceryItem method in DBHandlerGrocery and navigate to grocery list View
    void confirmDialogBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + item_name + " ?");
        builder.setMessage("Are you sure you want to delete this grocery item?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHandlerGrocery myDB = new DBHandlerGrocery(GroceryListUpdateItem.this);
                myDB.deleteGroceryItem(id); //if YES delete selected grocery item in database table
                finish();//redirect to grocery list view
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
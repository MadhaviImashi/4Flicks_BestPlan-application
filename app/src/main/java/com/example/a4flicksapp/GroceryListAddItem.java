package com.example.a4flicksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GroceryListAddItem extends AppCompatActivity {
    EditText name_input,price_input,quantity_input;
    Button add_grocery_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list_add_item);

        name_input = findViewById(R.id.textInputItemName);
        price_input = findViewById(R.id.textInputPrice);
        quantity_input = findViewById((R.id.textInputQuantity));
        add_grocery_item = findViewById((R.id.groceryAddBtn));

        //create onclickListener method to call addGroceryItem method in DBHelperGrocery class and navigate to your grocery list
        add_grocery_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate item name text view is not empty
                if(TextUtils.isEmpty(name_input.getText().toString())){
                    name_input.setError("Item Name can not be empty");
                    name_input.requestFocus();
                }
                //validate item price text view is not empty
                if(TextUtils.isEmpty(price_input.getText().toString())){
                    price_input.setError("Price can not be empty");
                    price_input.requestFocus();
                }
                //validate item quantity text view is not empty
                if(TextUtils.isEmpty(quantity_input.getText().toString())){
                    quantity_input.setError("Quantity can not be empty");
                    quantity_input.requestFocus();
                }else {
                    DBHandlerGrocery mydb = new DBHandlerGrocery(GroceryListAddItem.this);
                    mydb.addGroceryItem(name_input.getText().toString().trim(),
                            Float.valueOf(price_input.getText().toString().trim()),
                            Integer.valueOf(quantity_input.getText().toString().trim()));
                    Intent intent = new Intent(GroceryListAddItem.this, GroceryListItemList.class);
                    startActivity(intent);
                }
            }
        });
    }
    //create onclickListener method to navigate to your grocery list when click on back icon
    public void displayMyGroceryList(View view){
        Intent intent = new Intent(this,GroceryListItemList.class);
        startActivity(intent);
    }
}
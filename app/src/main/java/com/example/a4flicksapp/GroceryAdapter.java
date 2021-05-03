package com.example.a4flicksapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.MyViewHolder> {
    private Context context;
    private ArrayList item_id, item_name,item_price,quantity;

    GroceryAdapter(Context context, ArrayList item_id, ArrayList item_name, ArrayList item_price, ArrayList quantity) {
        this.context = context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.quantity = quantity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_grocery_list_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.item_name_txt.setText(String.valueOf(item_name.get(position)));
        holder.item_price_txt.setText(String.valueOf(item_price.get(position)));
        holder.quantity_txt.setText(String.valueOf(quantity.get(position)));
    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_id_txt,item_name_txt,item_price_txt,quantity_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name_txt = itemView.findViewById(R.id.item_Name);
            item_price_txt = itemView.findViewById(R.id.item_Price);
            quantity_txt = itemView.findViewById(R.id.item_Quantity);
        }
    }
}

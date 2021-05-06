package com.example.a4flicksapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class recipeAdapter extends ArrayAdapter<recipeModel> {

    private Context context;
    private int resource;
    List<recipeModel> recipeModels;

    recipeAdapter(Context context, int resource, List<recipeModel> recipeModels){
        super(context, resource, recipeModels);
        this.context = context;
        this.resource = resource;
        this.recipeModels = recipeModels;

    }

    //displays one recipe name in recipe list
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource, parent, false);

        TextView recipeNameIcon = row.findViewById(R.id.recipeNameIcon);

        //recipes[obj1, obj2, obj3]
        recipeModel recipeModel = recipeModels.get(position);
        recipeNameIcon.setText(recipeModel.getRecipe_Name());

        return row;
    }
}

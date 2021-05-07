package com.example.a4flicksapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class mealAdapter extends ArrayAdapter<mealPlan> {

    private Context context;
    private int resource;
    List<mealPlan> meals4;

    mealAdapter(Context context, int resource, List<mealPlan> meals4){
        super(context,resource,meals4);
        this.context=context;
        this.resource=resource;
        this.meals4=meals4;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView day3= row.findViewById(R.id.day2);
        TextView break3= row.findViewById(R.id.break2);
        TextView lunch3= row.findViewById(R.id.lunch2);
        TextView dinner3= row.findViewById(R.id.dinner2);
        ImageView imageView= row.findViewById(R.id.onGoing);

        mealPlan meals= meals4.get(position);
        day3.setText(meals.getDay1());
        break3.setText(meals.getBreakfirst1());
        lunch3.setText(meals.getLunch1());
        dinner3.setText(meals.getDinner1());


        if(meals.getFinished() > 0){
            imageView.setVisibility(View.VISIBLE);
        }
        return row;


    }
}

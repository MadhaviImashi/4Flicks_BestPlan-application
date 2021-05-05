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

public class ToDoAdapter extends ArrayAdapter<DailyTaskModel> {  //since we are using model class data in this adapter

    private Context context;
    private int res;
    List<DailyTaskModel> modelList;

    public ToDoAdapter(Context context, int resource, List<DailyTaskModel> modelListObj) {    //resource type is int because actually the xml files are created as integers
        super(context, resource, modelListObj);
            this.context = context;
            this.res = resource; //this will store the reference to the xml file of the singleTask
            this.modelList = modelListObj; //the arrayList comes to this, contains the data of all the rows in the current table
    }

    //create getView method to convert a single view xml part into java and filling data regard to a particular task by accessing data from db
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater = LayoutInflater.from(context);
        View oneRow = inflater.inflate(res, parent, false); //this inflate() method is used to convert the xml file into java as a View object

        //link the UI elements in the singleTask.xml to this adapter.java class using the oneRow view obj
        TextView tvTime = oneRow.findViewById(R.id.time);
        TextView tvDesc = oneRow.findViewById(R.id.description);
        ImageView tvImage = oneRow.findViewById(R.id.checkBox);

        //combine the DB and the oneRow
        DailyTaskModel taskObj = modelList.get(position); //from get() method, we can access the value belongs to the index no(position)
        tvTime.setText(taskObj.getTime());
        tvDesc.setText(taskObj.getDescription());
        tvImage.setVisibility(oneRow.VISIBLE); //checkbox will not be  visible

        if(taskObj.getFinished() > 0){ //if returning value of getFinished is 0, the user has not yet clicked on Finished btn
            tvImage.setVisibility(View.VISIBLE);
        }
        return oneRow;
    }
    //*this getView method will run repeatedly until all the rows in the table are catched //position will auto-increment
    //a new taskObj wil be created, everytime this getView() method runs
}

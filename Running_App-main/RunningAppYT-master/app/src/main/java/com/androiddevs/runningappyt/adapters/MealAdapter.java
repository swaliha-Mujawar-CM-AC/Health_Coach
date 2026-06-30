package com.androiddevs.runningappyt.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddevs.runningappyt.R;
import com.androiddevs.runningappyt.db.Meal;
import com.androiddevs.runningappyt.other.AddNewTask;
import com.androiddevs.runningappyt.ui.DietActivity;
import com.androiddevs.runningappyt.utilities.DatabaseHelper;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyViewHolder> {

    private List<Meal> mealList;
    private DietActivity activity;
    private DatabaseHelper myDB;

    public MealAdapter(DietActivity activity, DatabaseHelper myDB) {
        this.activity = activity;
        this.myDB = myDB;
    }

    @NonNull
    @Override
    public MealAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.MyViewHolder holder, int position) {

        final Meal item = mealList.get(position);
        holder.checkBox.setText(item.getMeal());
        holder.checkBox.setChecked(toBoolean(item.getStatus()));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    myDB.updateStatus(item.getId(),1);
                }else {
                    myDB.updateStatus(item.getId(),0);
                }
            }
        });

    }

    public boolean toBoolean(int num){
        return num != 0;
    }

    public Context getContext(){
        return activity;
    }

    public void setMeals(List<Meal> mList){
        this.mealList = mList;
        notifyDataSetChanged();
    }

    public void deleteMeal(int position){
        Meal item = mealList.get(position);
        myDB.deleteMeal(item.getId());
        mealList.remove(position);
        notifyItemRemoved(position);
    }

    public void editMeal(int position){
        Meal item = mealList.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id",item.getId());
        bundle.putString("meal",item.getMeal());


        AddNewTask task = new AddNewTask();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(),task.getTag());

    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

      CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.diet_checkbox);


        }
    }
}

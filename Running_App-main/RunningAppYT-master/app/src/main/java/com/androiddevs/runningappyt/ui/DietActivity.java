package com.androiddevs.runningappyt.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.androiddevs.runningappyt.R;
import com.androiddevs.runningappyt.adapters.MealAdapter;
import com.androiddevs.runningappyt.db.Meal;
import com.androiddevs.runningappyt.di.OnDialogCloseListener;
import com.androiddevs.runningappyt.other.AddNewTask;
import com.androiddevs.runningappyt.utilities.DatabaseHelper;
import com.androiddevs.runningappyt.utilities.RecyclerViewTouchHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DietActivity extends AppCompatActivity implements OnDialogCloseListener {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private DatabaseHelper myDB;

    private List<Meal> mList;
    private MealAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        recyclerView = findViewById(R.id.diet_recycler);
        fab = findViewById(R.id.fav_diet);
        myDB = new DatabaseHelper(DietActivity.this);
        mList = new ArrayList<>();
        adapter = new MealAdapter(DietActivity.this,myDB);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        mList = myDB.getAllMeals();
        adapter.setMeals(mList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
            mList = myDB.getAllMeals();
            adapter.setMeals(mList);
            adapter.notifyDataSetChanged();
    }
}
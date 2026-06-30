package com.androiddevs.runningappyt.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;

import com.androiddevs.runningappyt.db.Meal;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    private static final String DATABASE_NAME = "MEAL_DATABASE";
    private static final String TABLE_NAME = "MEAL_TABLE";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "MEAL";
    private static final String COL_3 = "STATUS";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , MEAL TEXT , STATUS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME );
            onCreate(sqLiteDatabase);
    }

    public void insertMeal(Meal meal){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2,meal.getMeal());
        values.put(COL_3,0);
        sqLiteDatabase.insert(TABLE_NAME,null,values);

    }

    public void updateMeal(int id, String meal){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2,meal);
        sqLiteDatabase.update(TABLE_NAME,values,"ID=?" , new String[]{String.valueOf(id)});
    }

    public void updateStatus(int id, int status){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_3,status);
        sqLiteDatabase.update(TABLE_NAME,values,"ID=?",new String[]{String.valueOf(id)});
    }

    public void deleteMeal(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,"ID=?",new String[]{String.valueOf(id)});
    }


    public List<Meal> getAllMeals(){

        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = null;
        List<Meal> mealList = new ArrayList<>();

        sqLiteDatabase.beginTransaction();
        try {
            cursor = sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
            if (cursor!= null){
                if (cursor.moveToFirst()){
                    do {
                            Meal meal = new Meal();
                            meal.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
                            meal.setMeal(cursor.getString(cursor.getColumnIndex(COL_2)));
                            meal.setStatus(cursor.getInt(cursor.getColumnIndex(COL_3)));
                            mealList.add(meal);

                    }while (cursor.moveToNext());

                }
            }
        }finally {
            sqLiteDatabase.endTransaction();
            cursor.close();

        }
        return mealList;

    }

}





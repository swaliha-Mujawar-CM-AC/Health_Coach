    package com.androiddevs.runningappyt.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.androiddevs.runningappyt.R;
import com.androiddevs.runningappyt.db.MyReceiver;

import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener{

    private Button alarmBtn, cancelBtn;
    private int notificationId = 1;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmBtn = findViewById(R.id.alarm_btn);
        cancelBtn = findViewById(R.id.cancel_btn);

        alarmBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {



        Intent intent = new Intent(AlarmActivity.this, MyReceiver.class);
        intent.putExtra("notificationId",notificationId);


        PendingIntent alarmIntent = PendingIntent.getBroadcast(AlarmActivity.this,0, intent,PendingIntent.FLAG_CANCEL_CURRENT);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (v.getId()){
            case R.id.alarm_btn:

                assert alarmManager != null;
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR,alarmIntent);

                Toast.makeText(AlarmActivity.this, "Reminder set successfully!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancel_btn:
                Toast.makeText(this, "Reminder Stopped!", Toast.LENGTH_SHORT).show();
                break;
        }




    }
}
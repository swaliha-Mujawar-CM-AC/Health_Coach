package com.androiddevs.runningappyt.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.androiddevs.runningappyt.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class GainActivity extends AppCompatActivity {

    private YouTubePlayerView video1, video2, video3, video4, video5,video6, video7, video8, video9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gain);

        video1 = findViewById(R.id.video1);
        getLifecycle().addObserver(video1);

        video2 = findViewById(R.id.video2);
        getLifecycle().addObserver(video2);

        video3 = findViewById(R.id.video3);
        getLifecycle().addObserver(video3);

        video4 = findViewById(R.id.video4);
        getLifecycle().addObserver(video4);

        video5 = findViewById(R.id.video5);
        getLifecycle().addObserver(video5);

        video6 = findViewById(R.id.video6);
        getLifecycle().addObserver(video6);

        video7 = findViewById(R.id.video7);
        getLifecycle().addObserver(video7);

        video8 = findViewById(R.id.video8);
        getLifecycle().addObserver(video8);

        video9 = findViewById(R.id.video9);
        getLifecycle().addObserver(video9);



    }
}
package com.androiddevs.runningappyt.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.androiddevs.runningappyt.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class LooseActivity extends AppCompatActivity {

    private YouTubePlayerView video1, video2, video3, video4, video5,video6, video7, video8, video9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loose);

        video1 = findViewById(R.id.video11);
        getLifecycle().addObserver(video1);

        video2 = findViewById(R.id.video12);
        getLifecycle().addObserver(video2);

        video3 = findViewById(R.id.video13);
        getLifecycle().addObserver(video3);

        video4 = findViewById(R.id.video14);
        getLifecycle().addObserver(video4);

        video5 = findViewById(R.id.video15);
        getLifecycle().addObserver(video5);

        video6 = findViewById(R.id.video16);
        getLifecycle().addObserver(video6);

        video7 = findViewById(R.id.video17);
        getLifecycle().addObserver(video7);

        video8 = findViewById(R.id.video18);
        getLifecycle().addObserver(video8);

        video9 = findViewById(R.id.video19);
        getLifecycle().addObserver(video9);

    }
}
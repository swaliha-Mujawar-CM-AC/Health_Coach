package com.androiddevs.runningappyt.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.androiddevs.runningappyt.R;
import com.androiddevs.runningappyt.ui.AlarmActivity;
import com.androiddevs.runningappyt.ui.DietActivity;
import com.androiddevs.runningappyt.ui.GainActivity;
import com.androiddevs.runningappyt.ui.LooseActivity;
import com.androiddevs.runningappyt.ui.MedicineActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class VideosFragment extends Fragment {



    public VideosFragment() {
        // Required empty public constructor
    }



    private Button gain, loose, water, diet, plan, fruit, medicine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        water = view.findViewById(R.id.water_reminer);
        medicine = view.findViewById(R.id.medicine);
        diet = view.findViewById(R.id.diet_tracker);
        plan = view.findViewById(R.id.diet_plans);
        fruit = view.findViewById(R.id.fruits);


        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MedicineActivity.class));
            }
        });

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.healthline.com/nutrition/zero-calorie-foods"));
                startActivity(intent);
            }
        });

        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.healthline.com/nutrition/best-diet-plans#TOC_TITLE_HDR_4"));
                startActivity(intent);
            }
        });

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AlarmActivity.class));
            }
        });

        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DietActivity.class));
            }
        });
        loose = view.findViewById(R.id.videos_loosing_weight);
        loose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LooseActivity.class));
            }
        });

        gain = view.findViewById(R.id.videos_gaining_weight);
        gain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), GainActivity.class));
            }
        });


        return view;
    }
}
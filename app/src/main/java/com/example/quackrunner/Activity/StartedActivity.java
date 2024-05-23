package com.example.quackrunner.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quackrunner.Adapter.IntroSliderAdapter;
import com.example.quackrunner.Model.TutorialSlider;
import com.example.quackrunner.R;
import com.example.quackrunner.databinding.ActivityStartedBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class StartedActivity extends AppCompatActivity {

    private ActivityStartedBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityStartedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        Intent play = new Intent(StartedActivity.this, QuackRunnerActivity.class);
        Intent tutorial = new Intent(StartedActivity.this, TutorialActivity.class);
        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(play);
            }
        });
        binding.tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(tutorial);
            }
        });
        binding.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }

}
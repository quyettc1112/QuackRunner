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
import com.example.quackrunner.databinding.ActivityTutorialBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class TutorialActivity extends AppCompatActivity {

    private ActivityTutorialBinding binding;

    private ArrayList<TutorialSlider> tutorialList = new ArrayList<>(
            Arrays.asList(
                    new TutorialSlider(R.drawable.ic_slider_sample, "Game này rất hay, đặt cược 1 ăn 100"),
                    new TutorialSlider(R.drawable.ic_slider_2, "Game này rất hay2")

            )
    );

    private IntroSliderAdapter adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityTutorialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new IntroSliderAdapter(tutorialList, TutorialActivity.this);
        binding.introSliderViewpager.setAdapter(adapter);


        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.introSliderViewpager.getCurrentItem() + 1  < adapter.getItemCount()) {
                    int temp = binding.introSliderViewpager.getCurrentItem();
                    binding.introSliderViewpager.setCurrentItem(temp + 1);
                }
            }
        });
    }

}
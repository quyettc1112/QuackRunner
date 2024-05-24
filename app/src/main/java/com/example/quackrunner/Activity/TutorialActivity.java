package com.example.quackrunner.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

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
                    new TutorialSlider(R.drawable.ic_slider_sample, "QuackRunner là một trò chơi đua vịt vui nhộn, cho phép bạn chọn vịt để cược tiền vào chúng và xem chúng bơi đua nhau đến đích xem số tiền thuộc vào ai."),
                    new TutorialSlider(R.drawable.ic_slider_totu_money, "Coin : là nơi chứa tổng số tiền của chúng ta hiện có, nơi mà bạn dành thời gian để suy nghĩ về việc có nên đặt cược không."),
                    new TutorialSlider(R.drawable.ic_slider_totu_bet, "Bet : là nơi chúng ta chọn và đặt cược cho nhũng chú vịt con của mình mà mình chọn."),
                    new TutorialSlider(R.drawable.ic_slider_2, "Bước 1: Khởi đầu thì game sẽ cho chúng ta 4 con vịt để chúng ta lựa chọn  để đặt cược."),
                    new TutorialSlider(R.drawable.tuto_selected, "Bước 2: Sau khi lựa chọn thì hãy đặt đặt vào con vịt bạn muốn và ấn vào nút bet để các chú vịt của chúng ta có thể băt đầu cuộc đua."),
                    new TutorialSlider(R.drawable.gif_duck, "Mong các bạn sẽ có khoảng thời gian vui vẻ khi tận hưởng game sau khi đọc hướng dẫn này.")

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
                } else  {
                    Intent i = new Intent(TutorialActivity.this, QuackRunnerActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });
    }

}
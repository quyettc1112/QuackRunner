package com.example.quackrunner.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quackrunner.R;
import com.example.quackrunner.databinding.ActivityStartedBinding;

public class StartedActivity extends AppCompatActivity {

    private ActivityStartedBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityStartedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
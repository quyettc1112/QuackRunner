package com.example.quackrunner.Activity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quackrunner.R;
import com.example.quackrunner.databinding.ActivityQuackRunnerBinding;

public class QuackRunnerActivity extends AppCompatActivity {

    // Khai báo bindingđể thay thế findViewbyId(),
    private ActivityQuackRunnerBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityQuackRunnerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SeekBar seekBar = binding.customSeekbar;
        LayerDrawable layerDrawable = (LayerDrawable) seekBar.getProgressDrawable();

        if (layerDrawable != null) {
            // Dùng ID của item hoặc thứ tự
            Drawable waveDrawable = layerDrawable.getDrawable(1); // Lấy drawable thứ 2 từ LayerDrawable
            if (waveDrawable instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) waveDrawable;
                animationDrawable.start();
            }
        } else {
            Toast.makeText(this, "LayerDrawable is null", Toast.LENGTH_LONG).show();
        }


    }
}
package com.example.quackrunner.Activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
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

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.quackrunner.R;
import com.example.quackrunner.databinding.ActivityQuackRunnerBinding;

public class QuackRunnerActivity extends AppCompatActivity {

    // Khai báo bindingđể thay thế findViewbyId(),
    private ActivityQuackRunnerBinding binding;
    private LottieAnimationView animationView;

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

        SeekBar seekBarLine1 = binding.customSeekbarLine1;
        SeekBar seekBarLine2 = binding.customSeekbarLine2;
        SeekBar seekBarLine3 = binding.customSeekbarLine3;
        SeekBar seekBarLine4 = binding.customSeekbarLine4;

        setupSeekBarAnimation(seekBarLine1, 200);
        setupSeekBarAnimation(seekBarLine2, 300);
        setupSeekBarAnimation(seekBarLine3, 200);
        setupSeekBarAnimation(seekBarLine4, 300);


    }

    private void setupSeekBarAnimation(SeekBar seekBar, long shakeDuration) {
        LayerDrawable layerDrawable = (LayerDrawable) seekBar.getProgressDrawable();

        if (layerDrawable != null) {
            Drawable waveDrawable = layerDrawable.getDrawable(1); // Lấy drawable thứ 2 từ LayerDrawable
            if (waveDrawable instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) waveDrawable;
                animationDrawable.start();
            }
        } else {
            Toast.makeText(this, "LayerDrawable is null", Toast.LENGTH_LONG).show();
        }

        ObjectAnimator shakeAnimator = ObjectAnimator.ofPropertyValuesHolder(
                seekBar,
                PropertyValuesHolder.ofFloat("rotation", -1.15f, 1.15f)
        );
        shakeAnimator.setDuration(shakeDuration);
        shakeAnimator.setRepeatCount(ValueAnimator.INFINITE);
        shakeAnimator.setRepeatMode(ValueAnimator.REVERSE);

        // Bắt đầu animation
        shakeAnimator.start();
    }
}
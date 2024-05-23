package com.example.quackrunner.Activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.quackrunner.Model.QuackDTO;
import com.example.quackrunner.R;
import com.example.quackrunner.databinding.ActivityQuackRunnerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import pl.droidsonroids.gif.GifImageView;

public class QuackRunnerActivity extends AppCompatActivity {

    // Khai báo bindingđể thay thế findViewbyId(),
    private ActivityQuackRunnerBinding binding;
    public ArrayList<QuackDTO> listDuck = new ArrayList<>();
    private Dialog dialog;
    // Sử dụng nhạc
    private MediaPlayer mediaPlayer;

    public int totalMoney = 100;

    private boolean isMusicPlaying = true; // Để theo dõi trạng thái nhạc
    Handler handler = new Handler();
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

//        SeekBar seekBarLine1 = binding.customSeekbarLine1;
//        SeekBar seekBarLine2 = binding.customSeekbarLine2;
//        SeekBar seekBarLine3 = binding.customSeekbarLine3;
//        SeekBar seekBarLine4 = binding.customSeekbarLine4;

        setupSeekBarAnimation(binding.customSeekbarLine1, 200);
        setupSeekBarAnimation(binding.customSeekbarLine2, 300);
        setupSeekBarAnimation(binding.customSeekbarLine3, 200);
        setupSeekBarAnimation(binding.customSeekbarLine4, 300);


        setUpMusic();
        setAnimated();
        resetDuckProgress();

        listDuck.add(new QuackDTO(binding.customSeekbarLine1, 0, R.drawable.ic_duck_1));
        listDuck.add(new QuackDTO(binding.customSeekbarLine2, 0, R.drawable.ic_duck_2));
        listDuck.add(new QuackDTO(binding.customSeekbarLine3, 0, R.drawable.ic_duck_3));
        listDuck.add(new QuackDTO(binding.customSeekbarLine4, 0, R.drawable.ic_duck_4));

        // show Custom Dialog Event
        binding.btnBetDuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    // Setup ảnh động seek bar
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


    private void setUpMusic() {
        mediaPlayer = MediaPlayer.create(this, R.raw.ms_quack_music);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.start();
        binding.imMusicOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMusicPlaying) {
                    mediaPlayer.pause();
                    binding.imMusicOnOff.setImageResource(R.drawable.ic_speaker_off); // Đổi hình ảnh để phản ánh trạng thái nhạc đã tắt
                } else {
                    mediaPlayer.start();
                    binding.imMusicOnOff.setImageResource(R.drawable.ic_speaker_on); // Đổi hình ảnh để phản ánh trạng thái nhạc đang phát
                }
                isMusicPlaying = !isMusicPlaying; // Cập nhật trạng thái nhạc
            }
        });
    }


    // set Ảnh động
    private void setAnimated() {
        binding.ltCoin.playAnimation();

    }


    public static int randomNextInt(int min, int max) {
        Random random = new Random();

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return random.nextInt(max - min + 1) + min;
    }

    //GAME PLAY
    private void showWinnerDialog() {
        // = 0 if lose
        int winnerMoney = 0;
        //get total money after play
        for (QuackDTO quack : listDuck) {
            winnerMoney = (quack.bet > 0 && quack.isWinner)
                    ? quack.bet * 2 : winnerMoney;

            totalMoney += (quack.bet > 0 && quack.isWinner) ? quack.bet * 2 : 0;
        }

        //EXAMPLE
        Toast.makeText(QuackRunnerActivity.this,
                (winnerMoney > 0) ? "YOU WIN " + winnerMoney : "YOU LOSE",
                Toast.LENGTH_SHORT).show();

        Toast.makeText(QuackRunnerActivity.this,
                (winnerMoney > 0) ? "YOU WIN " + totalMoney : "YOU LOSE",
                Toast.LENGTH_SHORT).show();




    }
    private Runnable increaseRunnable = new Runnable() {
        @Override
        public void run() {
            boolean isStop = false;

            for (QuackDTO quack : listDuck) {

                int speed = randomNextInt(1, 5);
                quack.seekBar.setProgress(quack.seekBar.getProgress() + speed);

                if (quack.seekBar.getProgress() >= quack.seekBar.getMax()) {
                    isStop = true;
                    quack.isWinner = true;
                    break;
                }
            }

            if (!isStop) {
                handler.postDelayed(this, 500); // Lập lịch tăng sau một khoảng thời gian
            }
            else {
                showWinnerDialog();
            }
        }
    };

    private void showCustomDialog() {
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog_bet);

        // Thiết lập kích thước cho Dialog
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT ,2000 );

        // Cho phép hủy Dialog khi nhấn ra ngoài
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        dialog.findViewById(R.id.btn_play).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            listDuck.get(0).bet = Integer.parseInt(
                                    ((EditText)dialog
                                            .findViewById(R.id.edt_bet_duck1))
                                            .getText()
                                            .toString().trim());
                        }
                        catch (Exception e) {
                            listDuck.get(0).bet = 0;
                        }

                        try {
                            listDuck.get(1).bet = Integer.parseInt(
                                    ((EditText)dialog
                                            .findViewById(R.id.edt_bet_duck2))
                                            .getText()
                                            .toString().trim());
                        }
                        catch (Exception e) {
                            listDuck.get(1).bet = 0;
                        }

                        try {
                            listDuck.get(2).bet = Integer.parseInt(
                                    ((EditText)dialog
                                            .findViewById(R.id.edt_bet_duck3))
                                            .getText()
                                            .toString().trim());
                        }
                        catch (Exception e) {
                            listDuck.get(2).bet = 0;
                        }

                        try {
                            listDuck.get(3).bet = Integer.parseInt(
                                    ((EditText)dialog
                                            .findViewById(R.id.edt_bet_duck4))
                                            .getText()
                                            .toString().trim());
                        }
                        catch (Exception e) {
                            listDuck.get(3).bet = 0;
                        }
                        int totalBet = 0;

                        totalBet = listDuck.stream().mapToInt(o -> o.bet).sum();

                        if (totalBet <= totalMoney) {
                            totalMoney -= totalBet;
                            dialog.dismiss();
                            handler.post(increaseRunnable);
                        }
                        else {
                            Toast.makeText(QuackRunnerActivity.this,
                                    "TOTAL BET MONEY MUST BE LOWER THAN TOTAL MONEY",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        dialog.show();
    }

    private void handleResetOnClick() {
        for (QuackDTO quackDTO : listDuck) {
            quackDTO.seekBar.setProgress(0);
            quackDTO.bet = 0;
        }

    }

    private void resetDuckProgress() {
        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleResetOnClick();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        // Dừng animation khi Activity bị dừng
        if (binding.ltCoin != null && binding.ltCoin.isAnimating()) {
            binding.ltCoin.pauseAnimation();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
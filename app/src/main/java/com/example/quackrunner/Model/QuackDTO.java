package com.example.quackrunner.Model;

import android.widget.SeekBar;

public class QuackDTO {
    public SeekBar seekBar;
    public int bet;

    public boolean isWinner = false;

    public QuackDTO() {

    }

    public QuackDTO (SeekBar seekBar, int bet) {
        this.seekBar = seekBar;
        this.bet = bet;
    }
}

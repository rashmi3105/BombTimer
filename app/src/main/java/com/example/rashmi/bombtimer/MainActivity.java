package com.example.rashmi.bombtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    Boolean counterIsActive = false;
    Button button2;
    CountDownTimer timer;
    public void resetTimer()
    {
        textView.setText("1:00");
        seekBar.setProgress(60);
        seekBar.setEnabled(true);
        timer.cancel();
        button2.setText("GO!");
        counterIsActive = false;
    }
    public void buttonClick(View view)
    {
        if(counterIsActive) {

            resetTimer();
        }
        else {
            counterIsActive = true;
            seekBar.setEnabled(false);
            button2.setText("STOP!!");
             timer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mpPlayer = MediaPlayer.create(getApplicationContext(), R.raw.booms3);
                    mpPlayer.start();
                    resetTimer();                }
            }.start();
        }
    }
    public void updateTimer(int secLeft)
    {
        int min = secLeft/60;
        int sec = secLeft - min*60;

        String secString = Integer.toString(sec);
        if(sec<=9)
        {
            secString = "0" + secString;
        }
        textView.setText(Integer.toString(min) + ":" + secString);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        button2 = findViewById(R.id.button2);
        seekBar.setMax(600);
        seekBar.setProgress(60);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                int min = i/60;
                int sec = i - min*60;

                String secString = Integer.toString(sec);
                if(secString.equals("0"))
                {
                    secString = "00";
                }
                textView.setText(Integer.toString(min) + ":" + secString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

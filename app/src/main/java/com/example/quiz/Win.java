package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Win extends AppCompatActivity {
    Button btnSpin;
    ImageView ivWheel;
    Random random;
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        ivWheel = findViewById(R.id.wheel);
        btnSpin = findViewById(R.id.btnspin);
        String win[]={"lost","30%","lost","10%","20%","lost","30%","free","10%","20%"};
        random = new Random();
        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSpin.setEnabled(false);
                int spin = random.nextInt(10)+10;
                spin = spin * 30;
                timer = new CountDownTimer(spin*20,1) {
                    @Override
                    public void onTick(long l) {
                        if((l/1000)>3 ){
                            float rotation = ivWheel.getRotation() + 4;
                            ivWheel.setRotation(rotation);
                        }
                        else {
                            float rotation = ivWheel.getRotation() +(l/1000) ;
                            ivWheel.setRotation(rotation);
                        }
                    }@Override
                    public void onFinish() {
                        int angle = (int) ivWheel.getRotation() % 360;
                        int index = angle / 30;
                        String resultString = win[index];
                        Intent i = new Intent(Win.this,ClaimPrize.class);
                        i.putExtra("prize",resultString);
                        startActivity(i);
                    }
                }.start();

            }
        });
    }


}


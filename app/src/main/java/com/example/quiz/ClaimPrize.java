package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.widget.TextView;

public class ClaimPrize extends AppCompatActivity {
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    public class Constants {
        public static final String LOST = "lost";
        public static final String FREE = "free";
    }
    TextView title,body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_prize);
        title=findViewById(R.id.title);
        body=findViewById(R.id.body);

        Intent intent = getIntent();
        String result = intent.getStringExtra("prize");
        System.out.println(result);
        if(result.equals(Constants.LOST)){
            title.setText("Unlucky");
            body.setText("you haven't win anything.");

        }else if(result.equals(Constants.FREE)){
            title.setText("Congrats");
            body.setText("Lucky day! You've won a complimentary meal from us. Choose anything you like and dig in.");
        }
        else{
            title.setText("Congrats");
            body.setText("You have "+result+" discount on your total bill.");
        }


    }
}
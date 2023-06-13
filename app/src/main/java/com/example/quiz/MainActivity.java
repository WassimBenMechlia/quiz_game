package com.example.quiz;
import android.graphics.Color;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onBackPressed() {
        Toast.makeText(this,"You can't go back !",Toast.LENGTH_SHORT).show();
    }
    int score=0;
    int totalQuestion= 5,tmp=0,numberOfQuestions=0;
    int currentQuestionIndex;
    String selectedAnswer ="";
    TextView total,correctChoise;
    TextView question;
    Button A,B,C,D,submit;
    Random random;
    Set<Integer> usedIndices = new HashSet<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total=findViewById(R.id.total);
        question=findViewById(R.id.question);
        A=findViewById(R.id.A);
        B=findViewById(R.id.B);
        C=findViewById(R.id.C);
        D=findViewById(R.id.D);
        A.setBackgroundColor(0xFFCA1919);
        B.setBackgroundColor(0xFFCA1919);
        C.setBackgroundColor(0xFFCA1919);
        D.setBackgroundColor(0xFFCA1919);
        submit=findViewById(R.id.submit);
        correctChoise=findViewById(R.id.correctChoise);
        correctChoise.setText("Reponses correct : "+score);
        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        submit.setOnClickListener(this);
        total.setText("Total Questions :"+(totalQuestion-1)+"/"+numberOfQuestions);
        loadNewQuestion();

    }

    @Override
    public void onClick(View view) {

        A.setBackgroundColor(0xFFCA1919);
        B.setBackgroundColor(0xFFCA1919);
        C.setBackgroundColor(0xFFCA1919);
        D.setBackgroundColor(0xFFCA1919);
        total.setText("Total Questions :"+(totalQuestion-1)+"/"+numberOfQuestions);
        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit){
            if(selectedAnswer!=""){
                if(selectedAnswer.equals(QuestionReponse.correctAnswers[currentQuestionIndex])){
                    score++;
                }
                selectedAnswer="";
                correctChoise.setText("Reponses correct : "+score);
                loadNewQuestion();

            }else{
                Toast.makeText(getApplicationContext(),"you have to select an option ",Toast.LENGTH_SHORT).show();
            }

        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(0xFFFFC107);
        }
    }
    public void loadNewQuestion(){
        currentQuestionIndex=indexGenerator();

        if (numberOfQuestions == totalQuestion){
            if(score <3){
                Intent i=new Intent(MainActivity.this,LuckyWheel.class);
                startActivity(i);
            }else{
                Intent i = new Intent(MainActivity.this,Win.class);
                startActivity(i);
            }

        }
        else{
            question.setText(QuestionReponse.question[currentQuestionIndex]);
            A.setText(QuestionReponse.choices[currentQuestionIndex][0]);
            B.setText(QuestionReponse.choices[currentQuestionIndex][1]);
            C.setText(QuestionReponse.choices[currentQuestionIndex][2]);
            D.setText(QuestionReponse.choices[currentQuestionIndex][3]);
        }
    }
    public int indexGenerator(){
        random=new Random();
        int index;
        //do{
        //    index = random.nextInt(10);
        //}while (!usedIndices.add(index));
        index=random.nextInt(1);
            numberOfQuestions++;
            return index;
    }

}
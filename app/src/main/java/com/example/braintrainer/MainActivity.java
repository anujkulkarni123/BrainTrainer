package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button startButton;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    TextView SumTV;
    TextView correctTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    int numOfCorrectQuestions = 0;
    TextView counter;
    TextView timer;
    Button playAgainButton;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;

        timer.setText("30s");
        counter.setText("0/0");
        correctTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(5100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timer.setText("0s");
                correctTextView.setText("Your Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));


            }

        }.start();

    }


    public void generateQuestion()  {
        Random rand = new Random();

        int a = rand.nextInt(51), b = rand.nextInt(51);

        SumTV.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer)   {
                answers.add(a+b);
            } else  {
                incorrectAnswer = rand.nextInt(102);

                while (incorrectAnswer == (a+b))    {
                    incorrectAnswer = rand.nextInt(102);
                }
                answers.add(incorrectAnswer);
            }
        }

        answer1.setText(Integer.toString(answers.get(0)));
        answer2.setText(Integer.toString(answers.get(1)));
        answer3.setText(Integer.toString(answers.get(2)));
        answer4.setText(Integer.toString(answers.get(3)));


    }

    public void chooseAnswer (View view)    {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            correctTextView.setText("Correct!");
            numOfCorrectQuestions++;

        } else {
            correctTextView.setText("Wrong!");
        }

        numberOfQuestions++;
        counter.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        SumTV = (TextView) findViewById(R.id.SumTV);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        correctTextView = (TextView) findViewById(R.id.correctTextView);
        timer = (TextView) findViewById(R.id.timer);
        counter = (TextView)findViewById(R.id.counter);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);

        generateQuestion();
        playAgain(findViewById(R.id.playAgainButton));

        new CountDownTimer(5100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {

                timer.setText("0s");
                correctTextView.setText("Your Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));


            }

        }.start();

    }


}














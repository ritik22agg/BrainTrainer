package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int option_answer;
    CountDownTimer timer;
    TextView question;
    TextView timer_display;
    TextView Score_Board;
    TextView result;
    Random r = new Random();
    int num_question = 0;
    int num_correct = 0;
    int correct_stored_answer;

    public void update(){

        int x = r.nextInt(50);
        int y = r.nextInt(50);
        int z = x + y;
        correct_stored_answer = z;
        question = findViewById(R.id.question);
        question.setText(x + " + " + y);
        int option_number = r.nextInt(4);
        GridLayout grid = findViewById(R.id.gridLayout);

        for(int i = 0;i<4;i++){
            Button b1 = (Button) findViewById(R.id.gridLayout).findViewWithTag("" + i);
            if(i == option_number){
                b1.setText("" + z);
            }else{
                b1.setText("" + r.nextInt(100));
            }
        }
    }


    public void play_again(View view){
        timer.start();
        update();

        Button h = findViewById(R.id.play_again);
        h.setVisibility(View.INVISIBLE);
        num_question = 0;
        num_correct = 0;
        Score_Board.setText("0/0");
        result.setText("");
    }
    public void go(View view){
        timer = new CountDownTimer(30000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;

                timer_display = findViewById(R.id.textView2);
                timer_display.setText("" +seconds + "s");
                GridLayout grid = findViewById(R.id.gridLayout);

                for(int i = 0;i<4;i++){
                    Button b1 = (Button) findViewById(R.id.gridLayout).findViewWithTag("" + i);
                    b1.setEnabled(true);
                }
            }

            @Override
            public void onFinish() {
                String score = (String) Score_Board.getText();
                result = findViewById(R.id.result);
                result.setText("Your Score: " + score);
                timer_display.setText("0s");
                Button h = findViewById(R.id.play_again);
                h.setVisibility(View.VISIBLE);

                GridLayout grid = findViewById(R.id.gridLayout);

                for(int i = 0;i<4;i++){
                    Button b1 = (Button) findViewById(R.id.gridLayout).findViewWithTag("" + i);
                    b1.setEnabled(false);
                }

            }
        }.start();

        update();

        // visiblity criteria

        Button go =  (Button)view;
        go.setVisibility(View.INVISIBLE);
        GridLayout grid = findViewById(R.id.gridLayout);
        grid.setVisibility(View.VISIBLE);
        question = findViewById(R.id.question);
        timer_display = findViewById(R.id.textView2);
        Score_Board = findViewById(R.id.textView3);
        result = findViewById(R.id.result);
        question.setVisibility(View.VISIBLE);
        Score_Board.setVisibility(View.VISIBLE);
        result.setVisibility(View.VISIBLE);
        timer_display.setVisibility(View.VISIBLE);
    }

    public void option(View view){
        Button b = (Button)view;
        String ans = (String) b.getText();
        option_answer = Integer.parseInt(ans);
        //Log.i("button pressed", ans);
        result = findViewById(R.id.result);
        if(option_answer == correct_stored_answer){
            result.setText("Correct!");
            num_correct++;
        }else{
            result.setText("Wrong Answer!");
        }

        num_question++;
        update();


        Score_Board = findViewById(R.id.textView3);
        Score_Board.setText(num_correct + "/" + num_question);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

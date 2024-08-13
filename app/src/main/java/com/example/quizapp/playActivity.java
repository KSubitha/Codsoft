package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class playActivity extends AppCompatActivity {
    String[] question_list = {
            "What does CPU stand for?",
            "Which of the following is a programming language?",
            "What does RAM stand for?",
            "Which company developed the Windows operating system?",
            "What is the main function of an operating system?",
            "Which of these is a type of non-volatile memory?",
            "Which of the following is an input device?",
            "Which programming language is primarily used for web development?",
            "What is the full form of HTML?",
            "Which of the following is not an operating system?"
    };

    String[] choose_list = {
            "Central Processing Unit", "Central Programming Unit", "Control Processing Unit", "Central Process Unit",
            "Java", "HTML", "HTTP", "FTP",
            "Random Access Memory", "Read Access Memory", "Rapid Access Memory", "Run Access Memory",
            "Microsoft", "Apple", "Google", "IBM",
            "Manages hardware and software", "Provides data storage", "Runs antivirus software", "Performs calculations",
            "RAM", "ROM", "HDD", "CPU",
            "Monitor", "Printer", "Keyboard", "Speaker",
            "JavaScript", "Python", "HTML", "SQL",
            "HyperText Markup Language", "HyperText Mark Language", "HyperLink Markup Language", "HyperLink Mark Language",
            "Linux", "Windows", "macOS", "Google Chrome"
    };

    String[] correct_list = {
            "Central Processing Unit",
            "Java",
            "Random Access Memory",
            "Microsoft",
            "Manages hardware and software",
            "ROM",
            "Keyboard",
            "JavaScript",
            "HyperText Markup Language",
            "Google Chrome"
    };

    TextView cpt_question, text_question;
    Button btn_choose1, btn_choose2, btn_choose3, btn_choose4, btn_next;

    int currentQuestion = 0;
    int scorePlayer = 0;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);

        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_choose4 = findViewById(R.id.btn_choose4);
        btn_next = findViewById(R.id.btn_next);

        findViewById(R.id.image_back).setOnClickListener(
                a -> finish()
        );

        remplirData();

        btn_next.setOnClickListener(view -> {
            if (isclickBtn) {
                isclickBtn = false;

                if (!valueChoose.equals(correct_list[currentQuestion])) {
                    Toast.makeText(playActivity.this, "Wrong", Toast.LENGTH_LONG).show();
                    btn_click.setBackgroundResource(R.drawable.background_btn_erreur);
                } else {
                    Toast.makeText(playActivity.this, "Correct", Toast.LENGTH_LONG).show();
                    btn_click.setBackgroundResource(R.drawable.background_btn_correct);
                    scorePlayer++;
                }

                new Handler().postDelayed(() -> {
                    if (currentQuestion < question_list.length - 1) {
                        currentQuestion++;
                        remplirData();
                        valueChoose = "";
                        resetButtonBackgrounds();
                    } else {
                        Intent intent = new Intent(playActivity.this, ResulteActivity.class);
                        intent.putExtra("Result", scorePlayer); // Correct key
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            } else {
                Toast.makeText(playActivity.this, "You must choose one", Toast.LENGTH_LONG).show();
            }
        });
    }

    void remplirData() {
        cpt_question.setText((currentQuestion + 1) + "/" + question_list.length);
        text_question.setText(question_list[currentQuestion]);

        // Prepare the options for the current question
        List<String> options = new ArrayList<>();
        // Get options for the current question
        int startIndex = 4 * currentQuestion;
        options.add(choose_list[startIndex]); // Option 1
        options.add(choose_list[startIndex + 1]); // Option 2
        options.add(choose_list[startIndex + 2]); // Option 3
        options.add(choose_list[startIndex + 3]); // Option 4

        // Shuffle the options
        Collections.shuffle(options);

        // Set the shuffled options to buttons
        btn_choose1.setText(options.get(0));
        btn_choose2.setText(options.get(1));
        btn_choose3.setText(options.get(2));
        btn_choose4.setText(options.get(3));
    }

    void resetButtonBackgrounds() {
        btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
        btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
        btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
        btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
    }

    public void ClickChoose(View view) {
        btn_click = (Button) view;

        if (isclickBtn) {
            resetButtonBackgrounds();
        }

        chooseBtn();
    }

    void chooseBtn() {
        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
}

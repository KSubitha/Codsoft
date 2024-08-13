package com.example.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddQuestions extends Activity {

    private EditText editTextQuestion, editTextOption1, editTextOption2, editTextOption3, editTextOption4, editTextCorrectOption;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);

        editTextQuestion = findViewById(R.id.editTextQuestion);
        editTextOption1 = findViewById(R.id.editTextOption1);
        editTextOption2 = findViewById(R.id.editTextOption2);
        editTextOption3 = findViewById(R.id.editTextOption3);
        editTextOption4 = findViewById(R.id.editTextOption4);
        editTextCorrectOption = findViewById(R.id.editTextCorrectOption);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(v -> submitQuestion());
    }

    private void submitQuestion() {
        String question = editTextQuestion.getText().toString().trim();
        String option1 = editTextOption1.getText().toString().trim();
        String option2 = editTextOption2.getText().toString().trim();
        String option3 = editTextOption3.getText().toString().trim();
        String option4 = editTextOption4.getText().toString().trim();
        String correctOption = editTextCorrectOption.getText().toString().trim();

        if (question.isEmpty() || option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || option4.isEmpty() || correctOption.isEmpty()) {
            // Show error message
            return;
        }

        // Pass the data to the activity that will handle the quiz questions
        Intent intent = new Intent();
        intent.putExtra("Question", question);
        intent.putExtra("Option1", option1);
        intent.putExtra("Option2", option2);
        intent.putExtra("Option3", option3);
        intent.putExtra("Option4", option4);
        intent.putExtra("CorrectOption", correctOption);

        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}

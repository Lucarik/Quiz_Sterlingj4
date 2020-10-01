package com.example.quiz_sterlingj4.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz_sterlingj4.R;

public class Question4 extends AppCompatActivity {
    // Initialize answer array
    int[] ans = new int[4];
    // Initialize answer result, this will be used in intent to second question storing number of
    // correct answers
    int corr = 0;

    // Initialize buttons
    Button confirm;
    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView ansText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);

        // Get correct answer number from previous activity
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        int value = bundle.getInt("correct_answers");
        corr += value;

        // Assign buttons
        ansText = findViewById(R.id.ansText4);

        button = findViewById(R.id.next4B);
        button1 = findViewById(R.id.q4a1B);
        button2 = findViewById(R.id.q4a2B);
        button3 = findViewById(R.id.q4a3B);
        button4 = findViewById(R.id.q4a4B);
        confirm = findViewById(R.id.conf4B);

        button.setEnabled(false);

        // Set button listeners to click method with their answer values
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer(button1, 0);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer(button2, 1);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer(button3, 2);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer(button4, 3);
            }
        });

        // Confirm button, shows confirmation ui
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setConfirm();
            }
        });

        // Go to next page
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuestion5Page();
            }
        });
    }

    // For when a multiple choice button is clicked
    public void clickAnswer(Button button, int num) {

        if (ans[num] == 0) {
            @SuppressLint("UseCompatLoadingForDrawables") Drawable d = getResources().getDrawable(R.drawable.roundedbutton_clicked);
            button.setBackground(d);
            ans[num] = 1;
        } else {
            @SuppressLint("UseCompatLoadingForDrawables") Drawable d = getResources().getDrawable(R.drawable.roundedbutton);
            button.setBackground(d);
            ans[num] = 0;
        }

    }
    // Checks if checked buttons are correct based on array
    public boolean checkAnswer() {
        return ((ans[0] == 1) && (ans[1] == 0) && (ans[2] == 0) && ans[3] == 1);
    }

    // Create confirmation dialog, enable next question, disable multiple choice
    public void setConfirm() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(Question4.this);
        builder.setTitle("Confirmation");
        builder.setMessage("Confirm choice?");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
                confirm.setEnabled(false);
                button.setEnabled(true);
                button.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.INVISIBLE);

                // If correct answer display so, else display wrong answer text
                if (checkAnswer()) {
                    ansText.setText("Correct Answer");
                    ansText.setTextColor(Color.parseColor("#6fe890"));
                    corr += 1;
                } else {
                    ansText.setText("Wrong Answer");
                    ansText.setTextColor(Color.parseColor("#ff4f4f"));
                }
                ansText.setVisibility(View.VISIBLE);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog = builder.create();
        dialog.show();
    }

    // Opens the next activity, saves correct answer progress
    public void openQuestion5Page() {
        Intent intent = new Intent(this, Question5.class);
        intent.putExtra("correct_answers", corr);
        startActivity(intent);
    }
}
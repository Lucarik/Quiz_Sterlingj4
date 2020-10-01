package com.example.quiz_sterlingj4.quiz;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz_sterlingj4.R;

public class Question2 extends AppCompatActivity {
    // Saves current answer index
    int ans;
    // Saves previous clicked button
    Button prev;
    Button confirm;
    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView ansText;

    int corr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        int value = bundle.getInt("correct_answers");
        corr += value;

        ansText = findViewById(R.id.ansText2);
        button = findViewById(R.id.next2B);
        button1 = findViewById(R.id.q2a1B);
        button2 = findViewById(R.id.q2a2B);
        button3 = findViewById(R.id.q2a3B);
        button4 = findViewById(R.id.q2a4B);
        confirm = findViewById(R.id.conf2B);
        // Set button click listeners
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer(button1, 1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer(button2, 2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer(button3, 3);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickAnswer(button4, 4);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setConfirm();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuestion3Page();
            }
        });
    }

    public void openQuestion3Page() {
        Intent intent = new Intent(this, Question3.class);
        intent.putExtra("correct_answers", corr);
        startActivity(intent);
    }

    // For when a multiple choice button is clicked
    public void clickAnswer(Button button, int num) {
        @SuppressLint("UseCompatLoadingForDrawables") Drawable d = getResources().getDrawable(R.drawable.roundedbutton);
        if (prev == null)
            prev = button;
        else if (!prev.equals(button)) {
            prev.setBackground(d);
            prev = button;
        }
        // Set clicked background and save answer
        @SuppressLint("UseCompatLoadingForDrawables") Drawable dc = getResources().getDrawable(R.drawable.roundedbutton_clicked);
        button.setBackground(dc);
        ans = num;

    }

    // Create confirmation dialog, enable next question, disable multiple choice
    public void setConfirm() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(Question2.this);
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

    // Checks if checked answer is correct
    public boolean checkAnswer() {
        return (ans == 2);
    }
}
package com.example.quiz_sterlingj4.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.quiz_sterlingj4.LoggedIn;
import com.example.quiz_sterlingj4.R;
import com.example.quiz_sterlingj4.data.QuizDao;
import com.example.quiz_sterlingj4.data.QuizResults;
import com.example.quiz_sterlingj4.data.UserDao;
import com.example.quiz_sterlingj4.data.UserDatabase2;

public class Results extends AppCompatActivity {
    int corr;
    Button button;
    TextView corrAns;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get correct answer number from previous activity
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        int value = bundle.getInt("correct_answers");
        corr += value;


        button = findViewById(R.id.returnStartB);
        corrAns = findViewById(R.id.corrAnsText);
        corrAns.setText(Integer.toString(corr));
        if (corr < 3) {
            corrAns.setTextColor(Color.parseColor("#ff4f4f"));
        } else if (corr > 3 && corr <= 5) {
            corrAns.setTextColor(Color.parseColor("#6fe890"));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStartPage();
            }
        });

        final UserDatabase2 db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase2.class, UserDatabase2.NAME).build();
        final QuizDao quizDao = db.getQuizDao();
        final UserDao userDao = db.getUserDao();


        new Thread(new Runnable() {
            @Override
            public void run() {
                QuizResults result = new QuizResults();
                result.setScore(corr);
                int id = userDao.getLoggedInUser();
                result.setUser(id);
                quizDao.insert(result);
            }
        }).start();


    }
    // Open next activity
    public void openStartPage() {
        Intent intent = new Intent(this, LoggedIn.class);
        startActivity(intent);
    }

}
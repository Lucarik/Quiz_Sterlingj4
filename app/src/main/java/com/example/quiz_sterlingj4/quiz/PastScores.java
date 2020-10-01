package com.example.quiz_sterlingj4.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz_sterlingj4.LoggedIn;
import com.example.quiz_sterlingj4.R;
import com.example.quiz_sterlingj4.data.QuizDao;
import com.example.quiz_sterlingj4.data.User;
import com.example.quiz_sterlingj4.data.UserDao;
import com.example.quiz_sterlingj4.data.UserDatabase2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PastScores extends AppCompatActivity {

    List<Integer> scores;
    List<Integer> scoresList;
    TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_scores);

        scoreText = findViewById(R.id.displayScoresText);
        Button button = findViewById(R.id.pastRB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserPage();
            }
        });

        scores = new ArrayList<>();
        scores.add(1);

        final UserDatabase2 db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase2.class, UserDatabase2.NAME).build();
        final UserDao userDao = db.getUserDao();
        final QuizDao quizDao = db.getQuizDao();
        new Thread(new Runnable() {
            @Override
            public void run() {

                int id = userDao.getLoggedInUser();
                scoresList = quizDao.getUserResults(id);
                //scores = new ArrayList<>(scoresList);
                printScores(scoresList);
            }
        }).start();
    }

    public void printScores(List<Integer> scoresList) {
        scores = new ArrayList<>(scoresList);
        StringBuilder s = new StringBuilder();
        if (scores.isEmpty()) {
            scoreText.setText("No results");
        } else {
            int i = 1;
            for (int scr : scores) {
                s.append("Quiz ").append(i++).append("- ").append("Score: ").append(scr);
                s.append(System.getProperty("line.separator"));
            }
            scoreText.setText(s);
        }
    }

    //Method to open page after logging in
    public void openUserPage() {
        Intent intent = new Intent(this, LoggedIn.class);
        startActivity(intent);
    }
}
package com.example.quiz_sterlingj4.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.quiz_sterlingj4.LoggedIn;
import com.example.quiz_sterlingj4.R;
import com.example.quiz_sterlingj4.data.QuizDao;
import com.example.quiz_sterlingj4.data.UserDao;
import com.example.quiz_sterlingj4.data.UserDatabase2;

import java.util.ArrayList;
import java.util.List;
// Display past scores of current user
public class PastScores extends AppCompatActivity {

    List<Integer> scores;
    List<Integer> scoresList;
    TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_scores);

        scoreText = findViewById(R.id.displayScoresText);

        // Listener to go back to menu
        Button button = findViewById(R.id.pastRB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserPage();
            }
        });

        // Get database and return current user scores
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

    // Method to take list of scores and add to scoreText
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
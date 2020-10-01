package com.example.quiz_sterlingj4.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quiz_sterlingj4.LoggedIn;
import com.example.quiz_sterlingj4.R;
import com.example.quiz_sterlingj4.data.User;
import com.example.quiz_sterlingj4.data.UserDao;
import com.example.quiz_sterlingj4.data.UserDatabase2;

public class PastScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_scores);

        Button button = findViewById(R.id.pastRB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserPage();
            }
        });

        final UserDatabase2 db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase2.class, UserDatabase2.NAME).build();
        final UserDao userDao = db.getUserDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int id = userDao.getLoggedInUser();
                userDao.UpdateStatus(id, false);
            }
        }).start();
    }

    //Method to open page after logging in
    public void openUserPage() {
        Intent intent = new Intent(this, LoggedIn.class);
        startActivity(intent);
    }
}
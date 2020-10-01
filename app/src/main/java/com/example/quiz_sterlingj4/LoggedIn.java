package com.example.quiz_sterlingj4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.quiz_sterlingj4.data.User;
import com.example.quiz_sterlingj4.data.UserDao;
import com.example.quiz_sterlingj4.data.UserDatabase;
import com.example.quiz_sterlingj4.quiz.Instructions;
import com.example.quiz_sterlingj4.quiz.Question1;

public class LoggedIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        //Event listener for logout button
        Button button = findViewById(R.id.logoutB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        Button button1 = findViewById(R.id.viewIB);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInstructionPage();
            }
        });
        Button button2 = findViewById(R.id.startB);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuestion1Page();
            }
        });
    }
    //Logout process
    public void logout() {
        final UserDatabase db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "database").build();
        final UserDao userDao = db.getUserDao();
        final User[] user = new User[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                int id = userDao.getLoggedInUser();
                userDao.UpdateStatus(id, false);
            }
        }).start();
        db.close();
        openLoginPage();
    }
    //Redirects to login page
    public void openLoginPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Method to open page after logging in
    public void openInstructionPage() {
        Intent intent = new Intent(this, Instructions.class);
        startActivity(intent);
    }

    public void openQuestion1Page() {
        Intent intent = new Intent(this, Question1.class);
        startActivity(intent);
    }
}
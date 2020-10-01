package com.example.quiz_sterlingj4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.quiz_sterlingj4.data.User;
import com.example.quiz_sterlingj4.data.User1;
import com.example.quiz_sterlingj4.data.UserDao;
import com.example.quiz_sterlingj4.data.UserDao1;
import com.example.quiz_sterlingj4.data.UserDatabase;
import com.example.quiz_sterlingj4.data.UserDatabase1;
import com.example.quiz_sterlingj4.data.UserDatabase2;
import com.example.quiz_sterlingj4.data.UserLite;
import com.example.quiz_sterlingj4.data.UserLiteDao;
import com.example.quiz_sterlingj4.data.newDatabase;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    //Method to check if an EditText box is empty
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    //Method of checking if specified file exists
    public boolean fileExist(String filename){
        File file = getBaseContext().getFileStreamPath(filename);
        return file.exists();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.lButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUser();
            }
        });

        Button button1 = findViewById(R.id.rButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterPage();
            }
        });
    }

    //Opens registration page
    public void openRegisterPage() {
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    //Method to open page after logging in
    public void openUserPage() {
        Intent intent = new Intent(this, LoggedIn.class);
        startActivity(intent);
    }

    //Checks if account exists in internal storage
    public void checkUser() {
        EditText text1 = findViewById(R.id.lEmail);
        EditText text2 = findViewById(R.id.lPassword);

        if (isEmpty(text1) || isEmpty(text2)) {
            openDialogue("Please fill in fields");
        }


        final String field1 = text1.getText().toString();
        final String field2 = text2.getText().toString();

        boolean valid_user = false;

        final UserDatabase db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "database").build();
        final UserDao userDao = db.getUserDao();
        final User[] usr = new User[1];
        final UserDatabase2 newDb = Room.databaseBuilder(getApplicationContext(),
                UserDatabase2.class, UserDatabase2.NAME).build();
        final UserDao userDao1 = newDb.getUserDao();

        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.setFirstName("Qwerty");
                user.setLastName("Sanders");
                user.setEmail("qe");
                //user.setBirthday("01/01/1998");
                user.setPassword("qwe123");
                userDao1.insert(user);

                openUserPage();

                /*
                user[0] = userDao.findByEmailPass(field1, field2);

                if (user != null) {
                    userDao.UpdateStatus(user[0].getId(), true);
                    openUserPage();
                } else {
                    openDialogue("Wrong email or password");
                }

                 */

            }
        }).start();

        db.close();


    }

    //Creates alert dialog
    public void openDialogue(String input) {
        Dialogue dialogue = new Dialogue(input);
        dialogue.show(getSupportFragmentManager(), input);
    }
}
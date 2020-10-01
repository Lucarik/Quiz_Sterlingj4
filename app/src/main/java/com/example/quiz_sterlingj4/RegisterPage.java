package com.example.quiz_sterlingj4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.quiz_sterlingj4.data.User;
import com.example.quiz_sterlingj4.data.UserDao;
import com.example.quiz_sterlingj4.data.UserDatabase;
import com.example.quiz_sterlingj4.data.UserDatabase2;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterPage extends AppCompatActivity {
    private static final String TAG = "Error";

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
        setContentView(R.layout.activity_register_page);

        //Initialize register button
        final Button rButton = findViewById(R.id.r_Button);
        rButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Find text edit fields by id
                EditText text1 = findViewById(R.id.field1);
                EditText text2 = findViewById(R.id.field2);
                EditText text3 = findViewById(R.id.field3);
                EditText text4 = findViewById(R.id.field4);
                EditText text5 = findViewById(R.id.field5);

                String field1 = text1.getText().toString();
                String field2 = text2.getText().toString();
                String field3 = text3.getText().toString();
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Make sure user insert date into edittext in this format.

                Date field4 = null;

                try {
                    String dob_var = (text4.getText().toString());

                    field4 = formatter.parse(dob_var);
                } catch (java.text.ParseException e) {
                    Log.i(TAG, e.toString());
                }
                String field5 = text5.getText().toString();
                //Boolean for if any of the input fields are empty
                boolean empty = (isEmpty(text1) || isEmpty(text2) || isEmpty(text3) ||
                        isEmpty(text4) || isEmpty(text5));
                boolean noError = true;
                Pattern pattern = Pattern.compile("^([A-Z])[a-z]{2,9}$");
                Matcher matcher = pattern.matcher(field1);
                //Checks if first name starts with a capital, contains only letters, and 3-30 characters
                if (!matcher.find()) {
                    text1.setError("Only accepts letters (3-30 characters)");
                    noError = false;
                }
                //Checks if last name starts with a capital, contains only letters, and 3-30 characters
                pattern = Pattern.compile("^([A-Z])[a-z]{2,9}$");
                matcher = pattern.matcher(field2);
                if (!matcher.find()) {
                    text2.setError("Only accepts letters (3-30 characters)");
                    noError = false;
                }
                //Checks if email is valid
                if (!Patterns.EMAIL_ADDRESS.matcher(field3).matches()) {
                    text3.setError("Invalid email (Ex. example@email.com)");
                    noError = false;
                }
                //Checks if password is valid
                pattern = Pattern.compile("^[A-Za-z0-9]{5,15}$");
                matcher = pattern.matcher(field5);
                if (!matcher.find()) {
                    text5.setError("Accepts letters and numbers (5-15 characters)");
                    noError = false;
                }
                //If no empty fields
                if (empty) {
                    openDialogue("One or more fields are empty");
                    noError = false;
                }
                else if (!empty && noError){
                    writeFile();
                    openLoginPage();
                }

            }
        });


    }

    public void writeFile() {
        //Gets text fields and assigns values to variables
        EditText text1 = findViewById(R.id.field1);
        EditText text2 = findViewById(R.id.field2);
        EditText text3 = findViewById(R.id.field3);
        EditText text4 = findViewById(R.id.field4);
        EditText text5 = findViewById(R.id.field5);

        String field1 = text1.getText().toString();
        String field2 = text2.getText().toString();
        String field3 = text3.getText().toString();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date field4 = null;

        try {
            String dob_var = (text4.getText().toString());

            field4 = formatter.parse(dob_var);
        } catch (java.text.ParseException e) {
            Log.i(TAG, e.toString());
        }
        String field5 = text5.getText().toString();


        final UserDatabase2 db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase2.class, UserDatabase2.NAME).build();
        final UserDao userDao = db.getUserDao();
        final User user = new User();
        user.setFirstName(field1);
        user.setLastName(field2);
        user.setEmail(field3);
        user.setBirthday(field4);
        user.setPassword(field5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.insert(user);
            }
        }).start();

    }
    //Creates an alert dialog with string input message
    public void openDialogue(String input) {
        Dialogue dialogue = new Dialogue(input);
        dialogue.show(getSupportFragmentManager(), input);
    }

    public void openLoginPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
package com.example.quiz_sterlingj4.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz_sterlingj4.LoggedIn;
import com.example.quiz_sterlingj4.R;
// Class to view quiz instructions
public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        // Initialize and call listener
        Button button = findViewById(R.id.instrB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserPage();
            }
        });
    }

    //Method to go back to menu
    public void openUserPage() {
        Intent intent = new Intent(this, LoggedIn.class);
        startActivity(intent);
    }
}
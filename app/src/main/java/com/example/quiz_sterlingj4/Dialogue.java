package com.example.quiz_sterlingj4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

//This class creates an alert dialog with a variable message
public class Dialogue extends AppCompatDialogFragment {
    private String message;
    public Dialogue(String message) {
        this.message = message;
    }
    //@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Creates alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Alert");
        builder.setMessage(message);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
    }
    public AlertDialog.Builder createNew() {
        return new AlertDialog.Builder(getActivity());
    }
}

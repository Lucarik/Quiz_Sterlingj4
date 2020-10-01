package com.example.quiz_sterlingj4.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "birthday")
    public Date birthday;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "logged_in")
    public boolean logged_in = false;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getId() { return uid; }

}
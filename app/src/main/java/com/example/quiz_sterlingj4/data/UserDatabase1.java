package com.example.quiz_sterlingj4.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(version = 1, entities = {User1.class})
public abstract class UserDatabase1 extends RoomDatabase {

    abstract public UserDao1 getUserDao1();

    public static final String NAME = "database1";
}

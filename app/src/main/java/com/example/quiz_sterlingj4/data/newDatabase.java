package com.example.quiz_sterlingj4.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(version = 1, entities = {UserLite.class, User.class})
@TypeConverters({Converters.class})
public abstract class newDatabase extends RoomDatabase {

    abstract public UserDao getUserDao();
    abstract public UserLiteDao getUserLiteDao();

    public static final String NAME = "newDatabase";
}

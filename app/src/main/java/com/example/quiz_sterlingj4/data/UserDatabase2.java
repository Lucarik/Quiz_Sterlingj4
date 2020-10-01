package com.example.quiz_sterlingj4.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(version = 1, entities = {User.class, QuizResults.class})
@TypeConverters({Converters.class})
public abstract class UserDatabase2 extends RoomDatabase {

    abstract public UserDao getUserDao();
    abstract public QuizDao getQuizDao();

    public static final String NAME = "database2";
}

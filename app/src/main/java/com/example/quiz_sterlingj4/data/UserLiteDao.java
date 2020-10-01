package com.example.quiz_sterlingj4.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserLiteDao {
    @Query("SELECT * FROM userlite")
    List<UserLite> getAll();

    @Query("SELECT uid FROM userlite WHERE first_name = :first")
    int getId(String first);

    @Insert
    void insert(UserLite user);


}


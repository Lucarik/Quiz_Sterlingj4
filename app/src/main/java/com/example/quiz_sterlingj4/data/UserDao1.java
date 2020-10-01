package com.example.quiz_sterlingj4.data;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao1 {
    @Query("SELECT * FROM user1")
    List<User1> getAll();

    @Query("SELECT uid FROM user1 WHERE first_name = :first")
    int getId(String first);

    @Query("SELECT * FROM user1 WHERE uid IN (:userIds)")
    List<User1> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user1 WHERE email = :email AND password = :pass")
    User1 findByEmailPass(String email, String pass);

    @Query("UPDATE user1 SET logged_in = :status WHERE uid = :id")
    void UpdateStatus(int id, boolean status);

    @Query("SELECT uid FROM user1 WHERE logged_in = 1")
    int getLoggedInUser();

    @Insert
    void insertAll(User1... users);

    @Insert
    void insert(User1 user);


}
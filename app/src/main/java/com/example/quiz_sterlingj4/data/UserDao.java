package com.example.quiz_sterlingj4.data;

import androidx.room.Dao;
        import androidx.room.Insert;
        import androidx.room.Query;

        import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT uid FROM user WHERE first_name = :first")
    int getId(String first);

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE email = :email AND password = :pass")
    User findByEmailPass(String email, String pass);

    @Query("UPDATE user SET logged_in = :status WHERE uid = :id")
    void UpdateStatus(int id, boolean status);

    @Query("SELECT uid FROM user WHERE logged_in = 1")
    int getLoggedInUser();

    @Insert
    void insertAll(User... users);

    @Insert
    void insert(User user);


}
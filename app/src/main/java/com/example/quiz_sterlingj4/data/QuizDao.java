package com.example.quiz_sterlingj4.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuizDao {
    @Query("SELECT * FROM quizresults")
    List<QuizResults> getAll();

    @Query("SELECT score FROM quizresults WHERE user = :id")
    List<Integer> getUserResults(int id);

    @Query("SELECT score FROM quizresults JOIN user ON " +
    "quizresults.user = user.uid WHERE user.logged_in = 1")
    List<Integer> getResults();

    @Insert
    void insert(QuizResults result);
}
package com.example.quiz_sterlingj4.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = User.class,
                parentColumns = "uid",
                childColumns = "user",
                onDelete = ForeignKey.CASCADE
        )
})
public class QuizResults {
    @PrimaryKey(autoGenerate = true)
    public int qid;

    @ColumnInfo(name = "score")
    public int score;

    @ColumnInfo(name = "user")
    public int user;

    public void setScore(int score) {
        this.score = score;
    }

    public void setUser(int user) {
        this.user = user;
    }

}

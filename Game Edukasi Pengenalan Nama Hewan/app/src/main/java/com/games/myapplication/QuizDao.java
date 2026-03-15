package com.games.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;


@Dao
public interface QuizDao {
    @Insert
    long insertQuiz(Quiz quiz);

    // Ambil semua skor
    @Query("SELECT * FROM quiz WHERE id_quiz = :id")
    Quiz getQuizById(int id);

    // Ambil data dengan skor tertinggi
    @Query("SELECT * FROM quiz ORDER BY skor DESC LIMIT 1")
    Quiz getHighestScore();
}


package com.example.user.triviaapp.DB.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.user.triviaapp.DB.entity.QuizEntity;

import java.util.List;

@Dao
public interface TriviaDAO {

    @Insert
    void insertQuiz(QuizEntity quizEntity);
    @Query("SELECT * FROM QuizeAnswer")
    LiveData<List<QuizEntity>> getQuizData();



}

package com.example.user.triviaapp.DB.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.user.triviaapp.DB.dao.TriviaDAO;
import com.example.user.triviaapp.DB.entity.QuizEntity;
import com.example.user.triviaapp.model.QuizQuestionEntity;


@androidx.room.Database(entities = {QuizEntity.class}, version = 5,exportSchema = false)
public abstract class TriviaDB extends RoomDatabase {

    private static TriviaDB instance;

    public abstract TriviaDAO getTriviaDAO();

    public static synchronized TriviaDB getDBInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TriviaDB.class,"trivia_db").
                    fallbackToDestructiveMigration().build();
        }
        return instance;

    }

}
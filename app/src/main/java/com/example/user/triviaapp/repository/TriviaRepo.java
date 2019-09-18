package com.example.user.triviaapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.user.triviaapp.DB.dao.TriviaDAO;
import com.example.user.triviaapp.DB.database.TriviaDB;
import com.example.user.triviaapp.DB.entity.QuizEntity;

import java.util.List;

public class TriviaRepo  {

    TriviaDAO triviaDAO;
    TriviaDB triviaDB;
    QuizEntity quizEntity;
    LiveData<List<QuizEntity>> quizList;

    public TriviaRepo(Application application) {
        triviaDB = TriviaDB.getDBInstance(application);
        triviaDAO = triviaDB.getTriviaDAO();
    }

    public void insertQuizAnswer(QuizEntity quizEntity){
        new InsertQuizAnsAsynkTask(triviaDAO).execute(quizEntity);
    }

    public LiveData<List<QuizEntity>> getQuizData(){
       return triviaDAO.getQuizData();
    }

    private static class InsertQuizAnsAsynkTask extends AsyncTask<QuizEntity,Void,Void> {

        private TriviaDAO triviaDAO;

        public InsertQuizAnsAsynkTask(TriviaDAO triviaDAO) {
            this.triviaDAO = triviaDAO;
        }

        @Override
        protected Void doInBackground(QuizEntity... quizEntities) {
            triviaDAO.insertQuiz(quizEntities[0]);
            return null;
        }
    }
}

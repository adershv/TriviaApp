package com.example.user.triviaapp.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.user.triviaapp.DB.entity.QuizEntity;
import com.example.user.triviaapp.model.QuizQuestionEntity;
import com.example.user.triviaapp.repository.TriviaRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TriviaViewModel extends AndroidViewModel {
    private TriviaRepo triviaRepo;
    public List<QuizQuestionEntity> questionEntityList = null;
    public String username,question1,question2,ans1,ans2;
    public TriviaViewModel(@NonNull Application application) {

        super(application);
        triviaRepo = new TriviaRepo(application);
        initializeQuestion();


    }

    private void initializeQuestion() {
        if(questionEntityList == null){
            questionEntityList = new ArrayList<>();
            questionEntityList.add(new QuizQuestionEntity(1,"Who is the bset crickter in the world ?","Virat Kolli","Adam Gilchrist",
                    "Jacques Kallis","Sachin Tendulkar","single"));
            questionEntityList.add(new QuizQuestionEntity(2,"What are the colors in the indian national flag ","white","Yellow","Orange","Green","multiple"));

        }

    }

    public void insertAnser(){
        Date currentTime = Calendar.getInstance().getTime();
        QuizEntity quizEntity = new QuizEntity(username,question1,question2,ans1,ans2,currentTime.toString());
        triviaRepo.insertQuizAnswer(quizEntity);
    }


    public LiveData<List<QuizEntity>> getQuizData(){
        return triviaRepo.getQuizData();
    }
}

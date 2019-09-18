package com.example.user.triviaapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.user.triviaapp.DB.entity.QuizEntity;
import com.example.user.triviaapp.R;
import com.example.user.triviaapp.viewmodel.TriviaViewModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    TriviaViewModel triviaViewModel;
    List<QuizEntity> quizEntities = new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //RecycleView
        recyclerView=findViewById(R.id.recycler);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        //RecycleView
        triviaViewModel = ViewModelProviders.of(this).get(TriviaViewModel.class);


        triviaViewModel.getQuizData().observe(this, new Observer<List<QuizEntity>>() {
            @Override
            public void onChanged(List<QuizEntity> quizEntitiess) {
                quizEntities = quizEntitiess;
                recyclerAdapter = new Adapter(quizEntities);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
    }
}

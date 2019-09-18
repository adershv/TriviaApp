package com.example.user.triviaapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.triviaapp.DB.entity.QuizEntity;
import com.example.user.triviaapp.R;
import com.example.user.triviaapp.viewmodel.TriviaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    QuizEntity quizEntity;
    TriviaViewModel triviaViewModel;
    boolean isFinish = false;

    private int quizCounter = -1;


    RadioGroup radioGroup;
    CheckBox chop1,chop2,chop3,chop4;
    ConstraintLayout singleSelection,multipleselection,finish;
    RadioButton op1,op2,op3,op4;

    TextView singleSelectionQuestion,multipleSelectionQuestion,question1,question2,ans1,ans2,name;
    EditText username;
    Button start,next ,history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.name);
        start = findViewById(R.id.start);
        radioGroup = findViewById(R.id.radiogrp);

        chop1 = findViewById(R.id.chop1);
        chop2 = findViewById(R.id.chop2);
        chop3 = findViewById(R.id.chop3);
        chop4 = findViewById(R.id.chop4);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        op4 = findViewById(R.id.op4);
        next = findViewById(R.id.next);
        singleSelection = findViewById(R.id.singleselection);
        finish = findViewById(R.id.finish);
        multipleselection = findViewById(R.id.multipleselection);
        singleSelectionQuestion = findViewById(R.id.single_selection_question);
        multipleSelectionQuestion = findViewById(R.id.multiple_selection_question);
        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        name =  findViewById(R.id.resname);
        history = findViewById(R.id.history);

        triviaViewModel = ViewModelProviders.of(this).get(TriviaViewModel.class);




        triviaViewModel.getQuizData().observe(this, new Observer<List<QuizEntity>>() {
            @Override
            public void onChanged(List<QuizEntity> quizEntities) {
                Toast.makeText(MainActivity.this,quizEntities.size()+" Quiz in History",Toast.LENGTH_SHORT).show();
            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(username.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Enter username",Toast.LENGTH_SHORT).show();
                }else {
                    triviaViewModel.username = username.getText().toString();
                    Toast.makeText(MainActivity.this,triviaViewModel.username,Toast.LENGTH_SHORT).show();
                    quizCounter++;
                    if(triviaViewModel.questionEntityList.get(quizCounter).getType().equals("single")){
                        manageSingleSelectionUI();
                    }else if(triviaViewModel.questionEntityList.get(quizCounter).getType().equals("multiple")){
                        manageMultipleSelectionUI();
                    }
                    username.setVisibility(View.GONE);
                    start.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);
                    next.setText("next");
                }
            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isFinish){
                    triviaViewModel.insertAnser();
                    clearQuizData();

                }else {
                    saveAnswer();
                    quizCounter++;
                    if(quizCounter<triviaViewModel.questionEntityList.size()){

                        if(triviaViewModel.questionEntityList.get(quizCounter).getType().equals("single")){

                            manageSingleSelectionUI();

                        }else if(triviaViewModel.questionEntityList.get(quizCounter).getType().equals("multiple")){

                            manageMultipleSelectionUI();

                        }


                    }else {

                        manageFinishUI();
                    }
                }


            }

        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });



    }



    private void clearQuizData() {
        quizCounter = -1;
        triviaViewModel.username = "";
        triviaViewModel.question1 = "";
        triviaViewModel.question2 = "";
        triviaViewModel.ans1 = "";
        triviaViewModel.ans2="";
        username.setText("");
        multipleselection.setVisibility(View.GONE);
        singleSelection.setVisibility(View.GONE);
        finish.setVisibility(View.GONE);
        username.setVisibility(View.VISIBLE);
        start.setVisibility(View.VISIBLE);
        next.setVisibility(View.GONE);
        //radioGroup.clearCheck();

        chop4.setChecked(false);
        chop3.setChecked(false);
        chop2.setChecked(false);
        chop1.setChecked(false);
        isFinish = false;
    }

    private void manageFinishUI(){
        multipleselection.setVisibility(View.GONE);
        singleSelection.setVisibility(View.GONE);
        finish.setVisibility(View.VISIBLE);
        next.setText("finish");

        name.setText("Hello  "+triviaViewModel.username);
        question1.setText(triviaViewModel.question1);
        ans1.setText("Answer : "+triviaViewModel.ans1);
        question2.setText(triviaViewModel.question2);
        ans2.setText("Answer : "+triviaViewModel.ans2);

        isFinish = true;
    }


    private void manageSingleSelectionUI(){
        multipleselection.setVisibility(View.GONE);
        singleSelection.setVisibility(View.VISIBLE);
        finish.setVisibility(View.GONE);

        singleSelectionQuestion.setText(triviaViewModel.questionEntityList.get(quizCounter).getQuestion());
        op1.setText(triviaViewModel.questionEntityList.get(quizCounter).getOpt1());
        op2.setText(triviaViewModel.questionEntityList.get(quizCounter).getOpt2());
        op3.setText(triviaViewModel.questionEntityList.get(quizCounter).getOpt3());
        op4.setText(triviaViewModel.questionEntityList.get(quizCounter).getAnswer());
    }

    private void manageMultipleSelectionUI(){
        multipleselection.setVisibility(View.VISIBLE);
        singleSelection.setVisibility(View.GONE);
        finish.setVisibility(View.GONE);

        multipleSelectionQuestion.setText(triviaViewModel.questionEntityList.get(quizCounter).getQuestion());
        chop1.setText(triviaViewModel.questionEntityList.get(quizCounter).getOpt1());
        chop2.setText(triviaViewModel.questionEntityList.get(quizCounter).getOpt2());
        chop3.setText(triviaViewModel.questionEntityList.get(quizCounter).getOpt3());
        chop4.setText(triviaViewModel.questionEntityList.get(quizCounter).getAnswer());
    }

    private void saveAnswer(){
        if(quizCounter==0){
            triviaViewModel.question1 = triviaViewModel.questionEntityList.get(quizCounter).getQuestion();
            if(triviaViewModel.questionEntityList.get(quizCounter).getType().equals("single")){
                RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                triviaViewModel.ans1 = radioButton.getText().toString();

            }else {


                String ans = "";
                if(chop1.isChecked()){
                    ans =ans+","+chop1.getText().toString();

                }
                if(chop2.isChecked()){
                    ans = ans+","+chop2.getText().toString();
                }
                if(chop3.isChecked()){
                    ans = ans+","+chop3.getText().toString();
                }
                if(chop4.isChecked()){
                    ans = ans+","+chop4.getText().toString();
                }

                triviaViewModel.ans1 = ans;
            }
        }
        if(quizCounter==1){
            triviaViewModel.question2 = triviaViewModel.questionEntityList.get(quizCounter).getQuestion();
            if(triviaViewModel.questionEntityList.get(quizCounter).getType().equals("single")){
                RadioButton radioButton =findViewById(radioGroup.getCheckedRadioButtonId());
                triviaViewModel.ans2 = radioButton.getText().toString();
            }else {

                String ans = "";
                if(chop1.isChecked()){
                    ans = ans+chop1.getText().toString()+",";

                }
                if(chop2.isChecked()){
                    ans = ans+chop2.getText().toString()+",";
                }
                if(chop3.isChecked()){
                    ans = ans+chop3.getText().toString()+",";
                }
                if(chop4.isChecked()){
                    ans = ans+chop4.getText().toString()+"";
                }

                triviaViewModel.ans2 = ans;
            }
        }
    }

}

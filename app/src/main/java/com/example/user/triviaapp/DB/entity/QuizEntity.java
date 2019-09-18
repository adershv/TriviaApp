package com.example.user.triviaapp.DB.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "QuizeAnswer" )
public class QuizEntity {

    @PrimaryKey(autoGenerate = true)
    private int id ;

    private String name;
    private String question1;
    private String question2;
    private String answer1;
    private String answer2;
    private String dateTime;



    public QuizEntity(String name, String question1, String question2, String answer1, String answer2, String dateTime) {
        this.name = name;
        this.question1 = question1;
        this.question2 = question2;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.dateTime = dateTime;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuestion1() {
        return question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }
    public String getDateTime() {
        return dateTime;
    }
}

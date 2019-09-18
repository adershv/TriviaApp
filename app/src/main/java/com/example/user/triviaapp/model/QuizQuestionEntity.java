package com.example.user.triviaapp.model;


public class QuizQuestionEntity {

    private int id;
    private String question;
    private String opt1;
    private String opt2;
    private String opt3;
    private String answer;
    private String type;


    public QuizQuestionEntity(int id, String question, String opt1, String opt2, String opt3, String answer, String type) {
        this.id = id;
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.answer = answer;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOpt1() {
        return opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public String getAnswer() {
        return answer;
    }

    public String getType() {
        return type;
    }
}

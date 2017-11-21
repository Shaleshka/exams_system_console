package com.company.entities;

import java.io.Serializable;

public class Answer implements Serializable {

    private String examName;
    private String answerText;
    private int score;

    public Answer(String examName, String answerText) {
        this.examName = examName;
        this.answerText = answerText;
        this.score = -1; //not checked by teacher
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        return (examName != null ? examName.equals(answer.examName) : answer.examName == null) && (answerText != null ? answerText.equals(answer.answerText) : answer.answerText == null);
    }

    @Override
    public int hashCode() {
        int result = examName != null ? examName.hashCode() : 0;
        result = 31 * result + (answerText != null ? answerText.hashCode() : 0);
        return result;
    }
}

package com.company.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Enrollee implements Serializable {

    private final List<Answer> answers;
    private String name;
    private String facultyName;
    private double avg;

    public Enrollee(String name, String facultyName) {
        this.name = name;
        this.facultyName = facultyName;
        answers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void refreshAvg() {
        avg = (double) answers.stream().mapToInt(Answer::getScore).sum() / (double) answers.size();
    }

    public double getAvg() {
        return avg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enrollee enrollee = (Enrollee) o;

        return (name != null ? name.equals(enrollee.name) : enrollee.name == null) && (facultyName != null ? facultyName.equals(enrollee.facultyName) : enrollee.facultyName == null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (facultyName != null ? facultyName.hashCode() : 0);
        return result;
    }
}

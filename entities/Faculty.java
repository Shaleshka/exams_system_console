package com.company.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Faculty implements Serializable {

    private final int amount;
    private String name;
    private String teacherUsername;
    private List<Exam> exams;
    private List<Enrollee> enrolled;

    public Faculty(String name, String teacherUsername, int amount) {
        this.name = name;
        this.teacherUsername = teacherUsername;
        this.amount = amount;
        exams = new ArrayList<>();
        enrolled = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public void setTeacherUsername(String teacherUsername) {
        this.teacherUsername = teacherUsername;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public void addExam(Exam exam) {
        exams.add(exam);
    }

    //accepts list of all enrollees who passed exams on this faculty
    public void enroll(List<Enrollee> enrollees) {
        enrollees.sort(Comparator.comparingDouble(Enrollee::getAvg).reversed());
        enrolled = enrollees.stream().limit(amount).collect(Collectors.toList());
    }

    public List<Enrollee> getEnrolled() {
        return enrolled;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faculty faculty = (Faculty) o;

        return name != null ? name.equals(faculty.name) : faculty.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

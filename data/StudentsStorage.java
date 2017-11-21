package com.company.data;

import com.company.entities.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentsStorage extends Storage {

    private List<Student> students;

    public StudentsStorage() {
        super("students.cfg");
        students = new ArrayList<>();
    }

    public void loadList(Object obj) {
        students = (List<Student>) obj;
    }

    public List<Student> getByFacultyName(String facultyName) {
        return students.stream().filter(student -> Objects.equals(student.getFacultyName(), facultyName)).collect(Collectors.toList());
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getByName(String name) {
        return students.stream().filter(student -> Objects.equals(student.getName(), name)).findFirst().orElse(null);
    }
}

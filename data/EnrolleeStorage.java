package com.company.data;

import com.company.entities.Enrollee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.company.data.Data.ENROLLEE_FILENAME;

public class EnrolleeStorage extends Storage {

    private List<Enrollee> enrollees;

    public EnrolleeStorage() {
        super(ENROLLEE_FILENAME);
        enrollees = new ArrayList<>();
    }

    public void loadList(Object obj) {
        enrollees = (List<Enrollee>) obj;
    }

    public List<Enrollee> getByFacultyName(String facultyName) {
        return enrollees.stream().filter(student -> Objects.equals(student.getFacultyName(), facultyName)).collect(Collectors.toList());
    }

    @Override
    public List<Enrollee> getAll() {
        return enrollees;
    }

    public void addStudent(Enrollee enrollee) {
        enrollees.add(enrollee);
    }

    public Enrollee getByName(String name) {
        return enrollees.stream().filter(student -> Objects.equals(student.getName(), name)).findFirst().orElse(null);
    }
}

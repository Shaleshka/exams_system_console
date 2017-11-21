package com.company.data;

import com.company.entities.Faculty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FacultyStorage extends Storage {

    private List<Faculty> faculties;

    public FacultyStorage() {
        super("faculties.cfg");
        faculties = new ArrayList<>();
    }

    public void loadList(Object obj) {
        faculties = (List<Faculty>) obj;
    }

    @Override
    public List<Faculty> getAll() {
        return faculties;
    }

    public List<Faculty> getByTeacher(String username) {
        return faculties.stream().filter(
                faculty -> Objects.equals(faculty.getTeacherUsername(), username)).collect(Collectors.toList());
    }

    public Faculty getByName(String name) {
        return faculties.stream().filter(faculty -> Objects.equals(faculty.getName(), name)).findFirst().orElse(null);
    }
}

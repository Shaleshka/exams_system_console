package com.company.data;

import com.company.entities.TeacherUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserStorage extends Storage {

    private List<TeacherUser> teacherUsers;

    public UserStorage() {
        super("auth.cfg");
        teacherUsers = new ArrayList<>();
    }

    public void addUser(TeacherUser teacherUser) {
        teacherUsers.add(teacherUser);
    }

    public TeacherUser getUserByUsername(String username) {
        return teacherUsers.stream().filter(
                teacherUser -> Objects.equals(teacherUser.getUsername(), username)).findFirst().orElse(null);
    }

    public void loadList(Object obj) {
        teacherUsers = (List<TeacherUser>) obj;
    }

    @Override
    public List<TeacherUser> getAll() {
        return teacherUsers;
    }
}

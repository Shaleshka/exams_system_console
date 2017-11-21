package com.company.data;

import com.company.utils.FileWorker;

import java.util.Scanner;

public class Data {

    public static FacultyStorage facultyStorage;
    public static StudentsStorage studentsStorage;
    public static UserStorage userStorage;
    public static Scanner scanner;
    static FileWorker fileWorker;

    public Data() throws Exception {
        fileWorker = new FileWorker();

        facultyStorage = new FacultyStorage();
        facultyStorage.loadList(fileWorker.readObject("faculties.cfg"));

        studentsStorage = new StudentsStorage();
        studentsStorage.loadList(fileWorker.readObject("students.cfg"));

        userStorage = new UserStorage();
        userStorage.loadList(fileWorker.readObject("auth.cfg"));

        scanner = new Scanner(System.in);
    }

}

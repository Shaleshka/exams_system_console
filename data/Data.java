package com.company.data;

import com.company.utils.FileWorker;

import java.util.Scanner;

public class Data {

    public static FacultyStorage facultyStorage;
    public static final String USERSTORAGE_FILENAME = "src/com/company/files/auth.cfg";
    public static UserStorage userStorage;
    public static Scanner scanner;
    static FileWorker fileWorker;
    public static final String ENROLLEE_FILENAME = "src/com/company/files/enrolee.cfg";
    public static final String FACULTIES_FILENAME = "src/com/company/files/faculties.cfg";
    public static EnrolleeStorage enrolleeStorage;

    public Data() throws Exception {
        fileWorker = new FileWorker();

        facultyStorage = new FacultyStorage();
        facultyStorage.loadList(fileWorker.readObject(FACULTIES_FILENAME));

        enrolleeStorage = new EnrolleeStorage();
        enrolleeStorage.loadList(fileWorker.readObject(ENROLLEE_FILENAME));

        userStorage = new UserStorage();
        userStorage.loadList(fileWorker.readObject(USERSTORAGE_FILENAME));

        scanner = new Scanner(System.in);
    }

}

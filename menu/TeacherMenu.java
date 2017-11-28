package com.company.menu;

import com.company.entities.Answer;
import com.company.entities.Enrollee;
import com.company.entities.Exam;
import com.company.entities.Faculty;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.company.data.Data.facultyStorage;
import static com.company.data.Data.scanner;

public class TeacherMenu {

    private final String teacherUsername;

    public TeacherMenu(String username) {
        this.teacherUsername = username;
        System.out.println("Приветствуем");
        while (true) {
            printMenu();
            if (scanner.hasNextInt()) {
                String s = scanner.nextLine();
                if (Objects.equals(s, "")) s = "0";
                int k = Integer.parseInt(s);
                switch (k) {
                    case 1: {
                        printExams();
                        break;
                    }
                    case 2: {
                        printAccepted();
                        break;
                    }
                    default: {
                        return;
                    }
                }
            } else {
                System.out.println("Вы не ввели число!");
            }
        }
    }

    private void printMenu() {
        System.out.println("1. Непроверенные экзамены");
        System.out.println("2. Зачисленные студенты");
        System.out.println("0. Выход");
    }

    private void checkStudent(Enrollee enrollee) {
        List<Answer> answers = enrollee.getAnswers();
        System.out.println("Студент: " + enrollee.getName() + ", факультет: " + enrollee.getFacultyName());
        System.out.println("Оцените ответы студента");
        Faculty faculty = facultyStorage.getByName(enrollee.getFacultyName());
        for (Answer answer : answers) {
            Exam exam = faculty.getExams().stream().filter(Exam -> Objects.equals(Exam.getName(), answer.getExamName())).findFirst().orElse(null);
            assert exam != null;
            System.out.println("Задание по предмету " + exam.getName());
            System.out.println(exam.getTask());
            System.out.println("Ответ студента: " + answer.getAnswerText());
            boolean state = false;
            while (!state) {
                System.out.println("Введите оценку: ");
                if (scanner.hasNextInt()) {
                    int k = Integer.parseInt(scanner.nextLine());
                    if (k > 0 && k <= 10) {
                        answer.setScore(k);
                        System.out.println("Ваша оценка принята");
                        state = true;
                    } else {
                        System.out.println("Неправильное значение; оценки находятся в диапазоне от 1 до 10");
                    }
                } else {
                    System.out.println("Вы ввели не число!");
                }
            }
        }
        System.out.println("Оценка студента завершена");
        enrollee.refreshAvg();
    }

    private List<Enrollee> getUnchecked() {
        //get all students from all faculties assigned to a teacher which have at least one exam scored -1
        return facultyStorage.getAll().stream().
                filter(faculty -> Objects.equals(faculty.getTeacherUsername(), teacherUsername)).flatMap(faculty -> faculty.getEnrollees().stream()
                .filter(enrollee -> enrollee.getAnswers().stream().filter(answer -> answer.getScore() == -1)
                        .findFirst().orElse(null) != null)).collect(Collectors.toList());
    }

    private void printExams() {
        //finding all students who have at least one not scored answer and facultyName matches one of faculties names for
        //our current teacher
        List<Enrollee> unchecked = getUnchecked();
        while (unchecked.size() > 0) {
            System.out.println("Выберите студента");
            System.out.println("0. Выход");
            for (int i = 0; i < unchecked.size(); i++)
                System.out.println((i + 1) + ". " + unchecked.get(i).getName());
            if (scanner.hasNextInt()) {
                int k = Integer.parseInt(scanner.nextLine());
                if (k == 0) {
                    System.out.println("Обязательно зайдите позже, чтобы проверить оставшихся студентов");
                    facultyStorage.rewrite();
                    return;
                }
                try {
                    checkStudent(unchecked.get(k - 1));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Введённое число не соотвествутет существующему студенту!");
                }
            } else {
                System.out.println("Вы не ввели число!");
            }
            unchecked = getUnchecked();
        }
        facultyStorage.rewrite();
        System.out.println("Все студенты уже проверены. Можно выводить список зачисленных");
    }

    private void printAccepted() {
        for (Faculty faculty : facultyStorage.getByTeacher(teacherUsername)) {
            System.out.println("Факультет " + faculty.getName());
            //refreshing
            //passing only students who doesn't have -1 score (e.g. unchecked exams)
            faculty.enroll();
            System.out.println("Всего мест: " + faculty.getAmount());
            System.out.println("Всего зачислено: " + faculty.getEnrolled().size());
            for (Enrollee enrollee : faculty.getEnrolled()) {
                System.out.println(enrollee.getName() + ", балл: " + enrollee.getAvg());
            }

        }
        facultyStorage.rewrite();
    }

}

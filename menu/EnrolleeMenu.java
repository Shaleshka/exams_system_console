package com.company.menu;

import com.company.entities.Answer;
import com.company.entities.Enrollee;
import com.company.entities.Exam;
import com.company.entities.Faculty;

import java.util.List;

import static com.company.data.Data.facultyStorage;
import static com.company.data.Data.scanner;

public class EnrolleeMenu {

    private final String name;

    public EnrolleeMenu(String name) {
        this.name = name;
        boolean state = false;
        while (!state) {
            System.out.println("Выберите факультет, на который хотите зарегистрироваться");
            System.out.println("0. Отмена");
            for (int i = 0; i < facultyStorage.getAll().size(); i++)
                System.out.println((i + 1) + ". " + facultyStorage.getAll().get(i).getName());
            if (scanner.hasNextInt()) {
                int k = Integer.parseInt(scanner.nextLine());
                if (k == 0) {
                    System.out.println("Всего доброго! Вы можете вернуться в любое время");
                    return;
                }
                try {
                    passExams(facultyStorage.getAll().get(k - 1));
                    state = true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Введённое число не соответсвует существующему факультету!");
                }
            } else {
                System.out.println("Вы не ввели число!");
                state = false;
            }
        }
    }

    private void passExams(Faculty faculty) {
        Enrollee enrollee = new Enrollee(name, faculty.getName());
        List<Exam> examList = faculty.getExams();
        System.out.println("Приступим к сдаче экзаменов");
        for (Exam exam : examList) {
            System.out.println("Экзамен по предмету " + exam.getName());
            System.out.println("Задание: " + exam.getTask());
            System.out.println("Введите ваш ответ: ");
            if (scanner.hasNextLine()) {
                String answer = scanner.nextLine();
                enrollee.addAnswer(new Answer(exam.getName(), answer));
            }
            System.out.println("Ответ принят");
        }
        System.out.println("Все экзамены пройдены! Ожидайте информации о зачислении");
        faculty.registerEnrollee(enrollee);
        facultyStorage.rewrite();
    }

}

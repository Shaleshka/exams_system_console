package com.company;

import com.company.data.Data;
import com.company.entities.*;
import com.company.utils.AuthorizationHandler;
import com.company.utils.FileWorker;

import java.util.ArrayList;
import java.util.List;

import static com.company.data.Data.scanner;

public class Main {

    static AuthorizationHandler authorizationHandler;

    static void printMenu() {
        System.out.println("Выберите режим работы: ");
        System.out.println("1. Войти в систему как преподаватель");
        System.out.println("2. Регистрация (для студентов)");
        System.out.println("0. Выход");
    }

    public static void writeExampleInfo() {
        FileWorker worker = new FileWorker();
        List<TeacherUser> list = new ArrayList<>();
        list.add(new TeacherUser("petrov@bsuir.by", "123456"));
        list.add(new TeacherUser("alexeev@bsuir.by", "654321"));
        list.add(new TeacherUser("smirnov@bsuir.by", "987654"));
        worker.writeObject("auth.cfg", list);
        List<Faculty> faculties = new ArrayList<>();
        Faculty fkp = new Faculty("ФКП", "petrov@bsuir.by", 20);
        fkp.addExam(new Exam("Математика", "Продифференцировать константу и дважды транспонировать матрицу"));
        fkp.addExam(new Exam("Физика", "Вычислить импульс яблока при ударе по голове Ньютону, " +
                "если масса яблока 0.2кг, а высота падения - 4 м"));
        Faculty ksis = new Faculty("КСИС", "alexeev@bsuir.by", 30);
        ksis.addExam(new Exam("Математика", "Расскажите о функциях многих переменных"));
        ksis.addExam(new Exam("Информатика", "Способы задания графа"));
        Faculty fre = new Faculty("ФРЭ", "smirnov@bsuir.by", 15);
        fre.addExam(new Exam("Физика", "Расскажите о полупроводниках"));
        fre.addExam(new Exam("Радиоэлектроника", "Усилители на биполярных транзисторах"));
        faculties.add(fkp);
        faculties.add(ksis);
        faculties.add(fre);
        worker.writeObject("faculties.cfg", faculties);

        List<Student> students = new ArrayList<>();
        Student student1 = new Student("Валерий Смирнов", "ФКП");
        student1.addAnswer(new Answer("Математика", "Очень смешно) 0, та же матрица"));
        student1.addAnswer(new Answer("Физика", "p=mv; gh=1/2*v^2; v = sqrt(2gh) ~ 9 м/с, " +
                "тогда импульс примерно 4.5кг*м/с"));
        Student student2 = new Student("Алексей Федотов", "ФКП");
        student2.addAnswer(new Answer("Математика", "Производная константы 0, матрица не изменится"));
        student2.addAnswer(new Answer("Физика", "p=mv; gh=1/2*v^2; v = sqrt(2gh) ~ 9 м/с, " +
                "тогда импульс примерно 4.5кг*м/с"));

        Student student3 = new Student("Павел Бизулин", "КСИС");
        student3.addAnswer(new Answer("Математика", "Функцией многих переменных называется закон, по " +
                "которому каждому из значений независимых переменных  (аргументов) из области определения соответствует " +
                "значение зависимой переменной  (функции)."));
        student3.addAnswer(new Answer("Информатика", "геометрический (рисунки, схемы, диаграммы), " +
                "простое перечисление вершин и ребер, табличный"));

        Student student4 = new Student("Михаил Иванов", "КСИС");
        student4.addAnswer(new Answer("Математика", "ФМП - закон, по " +
                "которому каждому из значений независимых переменных  (аргументов) из области определения соответствует " +
                "значение зависимой переменной  (функции)."));
        student4.addAnswer(new Answer("Информатика", "перечисление всех рёбер, таблица смежности" +
                ", таблица инцидентности, др"));

        Student student5 = new Student("Алекснадр Петров", "ФРЭ");
        student5.addAnswer(new Answer("Физика", "Полупроводники — материалы, по своей удельной " +
                "проводимости занимающие промежуточное место между " +
                "проводниками и диэлектриками, и отличающиеся от проводников сильной зависимостью удельной проводимости" +
                " от концентрации примесей, температуры и воздействия различных видов излучения."));
        student5.addAnswer(new Answer("Радиоэлектроника", "Принцип работы транзисторного усилителя " +
                "основан на том, что с помощью небольших изменений напряжения или тока во входной цепи транзистора можно " +
                "получить значительно большие изменения напряжения или тока в его выходной цепи."));

        Student student6 = new Student("Вадим Днищенко", "ФРЭ");
        student6.addAnswer(new Answer("Физика", "Полупроводник — это кристаллический материал, " +
                "который проводит электричество не столь хорошо, как металлы."));
        student6.addAnswer(new Answer("Радиоэлектроника", "Усилитель осуществляет увеличение энергии " +
                "управляющего сигнала за счет энергии вспомогательного источника. Входной сигнал является как бы " +
                "шаблоном, в соответствии с которым регулируется поступление энергии от источника к потребителю " +
                "усиленного сигнала"));

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);

        worker.writeObject("students.cfg", students);

    }

    public static void main(String[] args) {

        System.out.println("Система Вступительные экзамены");

        //writeExampleInfo();

        try {
            new Data();
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке данных!");
            return;
        }

        authorizationHandler = new AuthorizationHandler();
        boolean state = false;
        while (!state) {
            printMenu();
            if (scanner.hasNextInt()) {
                int k = Integer.parseInt(scanner.nextLine());
                switch (k) {
                    case 1: {
                        state = authorizationHandler.modeLogin();
                        break;
                    }
                    case 2: {
                        state = authorizationHandler.modeRegister();
                        break;
                    }
                    default: {
                        return;
                    }
                }
            } else {
                scanner.nextLine();
                System.out.println("Вы не ввели число!");
                state = false;
            }
        }
    }
}

package com.company.utils;

import com.company.data.Data;
import com.company.entities.TeacherUser;
import com.company.menu.StudentsMenu;
import com.company.menu.TeacherMenu;

import java.util.Objects;

import static com.company.data.Data.scanner;

public class AuthorizationHandler {

    public boolean modeLogin() {
        System.out.println("Введите логин");
        if (scanner.hasNextLine()) {
            String username = scanner.nextLine();
            TeacherUser teacherUser = Data.userStorage.getUserByUsername(username);
            if (teacherUser == null) {
                System.out.println("Пользователь не существует");
                return false;
            }
            System.out.println("Введите пароль: ");
            if (scanner.hasNextLine()) {
                String password = scanner.nextLine();
                if (!Objects.equals(teacherUser.getPassword(), password)) {
                    System.out.println("Неверный пароль!");
                    return false;
                }
                new TeacherMenu(username);
                return true;
            }
        }
        return false;
    }

    public boolean modeRegister() {
        System.out.println("Приветствуем, абитурент");
        System.out.println("Введите своё имя: ");
        if (scanner.hasNextLine()) {
            String name = scanner.nextLine();
            new StudentsMenu(name);
            return true;
        }
        return false;
    }


}

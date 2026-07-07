package Task5.user.api;


import Task5.user.application.UserService;
import Task5.user.domain.User;

import java.util.List;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                signIn();
            }
            break;
            case "4": {
                System.out.println();
            }
            break;
            case "5": {
                showUsersByProfileDescription();
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить данные пользователя");
        System.out.println("5. Показать пользователей по описанию профиля");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите name:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(name, email, password, profileDescription);
    }


    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("Email или пароль не верны");
        }
    }

    private void showUsersByProfileDescription() {
        System.out.println("Введите описание профиля для поиска:");
        String description = scanner.nextLine().trim();

        List<User> users = userService.findAllByProfileDescription(description);
        if (users.isEmpty()) {
            System.out.println("Пользователи с таким описанием не найдены.");
        } else {
            System.out.println("Найдено пользователей: " + users.size());
            for (User user : users) {
                System.out.println("Имя: " + user.getName() + ", Email: " + user.getEmail());
            }
        }
    }


}

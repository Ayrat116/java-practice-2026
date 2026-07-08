package Task6.ru.itis.shop.user.api;

import Task6.ru.itis.shop.user.application.UserService;
import Task6.ru.itis.shop.user.api.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1" -> signUp();
                case "2" -> signIn();
                case "3" -> findById();
                case "4" -> updateProfile();
                case "5" -> findAllUsers();
                case "6" -> findAllByProfileDescription();
                case "0" -> {
                    System.out.println("Выход...");
                    System.exit(0);
                }
                default -> System.out.println("Неизвестная команда. Попробуйте снова.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Меню ===");
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить описание пользователя по почте");
        System.out.println("5. Получить информацию обо всех пользователях");
        System.out.println("6. Показать информацию о пользователях с заданным описанием профиля");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        System.out.print("Введите описание профиля: ");
        String desc = scanner.nextLine();

        try {
            userService.signUp(name, email, password, desc);
            System.out.println("Пользователь успешно зарегистрирован!");
        } catch (Exception e) {
            System.out.println("Ошибка при регистрации: " + e.getMessage());
        }
    }

    private void signIn() {
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        boolean success = userService.signIn(email, password);
        if (success) {
            System.out.println("Вход выполнен успешно!");
        } else {
            System.out.println("Неверный email или пароль.");
        }
    }

    private void findById() {
        System.out.print("Введите id пользователя: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        Optional<UserDto> userDto = userService.findById(id);
        if (userDto.isPresent()) {
            System.out.println("Найден пользователь: " + userDto.get());
        } else {
            System.out.println("Пользователь с id " + id + " не найден.");
        }
    }

    private void updateProfile() {
        System.out.print("Введите email пользователя: ");
        String email = scanner.nextLine();
        System.out.print("Введите новое описание профиля: ");
        String newDesc = scanner.nextLine();

        try {
            userService.updateProfile(email, newDesc);
            System.out.println("Описание профиля обновлено!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void findAllUsers() {
        List<UserDto> users = userService.findAllUsers();
        if (users.isEmpty()) {
            System.out.println("Пользователей нет.");
        } else {
            System.out.println("Список всех пользователей:");
            for (UserDto dto : users) {
                System.out.println(dto);
            }
        }
    }

    private void findAllByProfileDescription() {
        System.out.print("Введите описание профиля для поиска: ");
        String description = scanner.nextLine();

        List<UserDto> users = userService.findAllByProfileDescription(description);
        if (users.isEmpty()) {
            System.out.println("Пользователи с таким описанием не найдены.");
        } else {
            System.out.println("Найдено пользователей: " + users.size());
            for (UserDto dto : users) {
                System.out.println(dto);
            }
        }
    }
}
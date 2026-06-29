package Task1.user.api;

import Task1.user.domain.User;
import Task1.user.repository.UserRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserRepository userRepository;
    private final Scanner scanner;

    public UserConsoleOperations(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("0. Выход");

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                System.out.println("Сейчас будем регистрировать пользователя");
                System.out.println("Введите email:");
                String email = scanner.nextLine();
                System.out.println("Введите password:");
                String password = scanner.nextLine();
                System.out.println("Введите описание профиля:");
                String profileDescription = scanner.nextLine();
                User user = new User(email, password, profileDescription);
                userRepository.save(user);
            }
            break;
            case "2": {
                System.out.println("Вы можете войти в приложение");
            }
            break;
            case "3": {
                System.out.println("Введите id:");
                String id = scanner.nextLine().trim();
                User user = userRepository.findById(id);
                if (user != null) {
                    System.out.println("Email: " + user.getEmail());
                    System.out.println("Описание профиля: " + user.getProfileDescription());
                } else {
                    System.out.println("Пользователь не найден");
                }
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }
}

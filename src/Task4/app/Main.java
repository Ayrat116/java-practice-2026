package Task4.app;

import Task4.user.api.UserConsoleOperations;
import Task4.user.application.UserService;
import Task4.user.infrastructure.persistence.UserFileRepository;
import Task4.user.infrastructure.persistence.UserMapper;
import Task4.user.infrastructure.persistence.UserRepositoryJdbcImpl;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/java_2026";
        String username = "postgres";
        String password = "854458";

        UserRepositoryJdbcImpl userRepository = new UserRepositoryJdbcImpl(url, username, password);
        UserService userService = new UserService(userRepository);

        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}

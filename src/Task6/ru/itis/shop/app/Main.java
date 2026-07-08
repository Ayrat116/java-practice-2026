package Task6.ru.itis.shop.app;

import Task6.ru.itis.shop.infrastructure.persistence.jdbc.DriverManagerDataSource;
import Task6.ru.itis.shop.user.api.UserConsoleOperations;
import Task6.ru.itis.shop.user.application.UserService;
import Task6.ru.itis.shop.user.infrastructure.persistence.jdbc.UserRepositoryJdbcImpl;
import Task6.ru.itis.shop.user.repository.UserRepository;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new DriverManagerDataSource(
                "jdbc:postgresql://localhost:5432/java_2026",
                "postgres",
                "854458"
        );

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        UserService userService = new UserService(userRepository);
        UserConsoleOperations console = new UserConsoleOperations(userService);

        console.showMenu();
    }
}
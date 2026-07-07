package Task5.app;

import Task5.infrastructure.persistence.jdbc.DriverManagerDataSource;
import Task5.user.api.UserConsoleOperations;
import Task5.user.application.UserService;
import Task5.user.infrastructure.persistence.jdbc.UserRepositoryJdbcImpl;
import Task5.user.repository.UserRepository;
import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new DriverManagerDataSource(
                "jdbc:postgresql://localhost:5432/java_2026",
                "postgres",
                "qwerty007"
        );

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);

        UserService userService = new UserService(userRepository);
        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}

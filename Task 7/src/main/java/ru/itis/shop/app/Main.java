package ru.itis.shop.app;

import ru.itis.shop.infrastructure.persistence.jdbc.DriverManagerDataSource;
import ru.itis.shop.user.api.UserConsoleOperations;
import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.infrastructure.persistence.jdbc.UserRepositoryJdbcImpl;
import ru.itis.shop.user.repository.UserRepository;
import ru.itis.shop.util.PropertiesReader;

import javax.sql.DataSource;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        PropertiesReader reader = new PropertiesReader("application.properties");
        Properties props = reader.loadProperties();

        String url = props.getProperty("db.url");
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        DataSource dataSource = new DriverManagerDataSource(url, username, password);

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        UserService userService = new UserService(userRepository);
        UserConsoleOperations console = new UserConsoleOperations(userService);

        console.showMenu();
    }
}
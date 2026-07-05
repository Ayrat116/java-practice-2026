package Task4.user.infrastructure.persistence;

import Task4.user.domain.User;
import Task4.user.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final String url;
    private final String username;
    private final String password;

    public UserRepositoryJdbcImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, email, password, profile_description FROM users";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String email = resultSet.getString("email");
                String pwd = resultSet.getString("password");
                String profileDesc = resultSet.getString("profile_description");

                User user = new User(id, email, pwd, profileDesc);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return users;
    }

    @Override
    public void save(User user) {
        System.out.println();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean updateProfileByEmail(String email, String newDescription) {
        return false;
    }
}

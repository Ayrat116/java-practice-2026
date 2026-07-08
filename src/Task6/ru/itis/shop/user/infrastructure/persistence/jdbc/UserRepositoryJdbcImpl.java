package Task6.ru.itis.shop.user.infrastructure.persistence.jdbc;

import Task6.ru.itis.shop.infrastructure.persistence.jdbc.RowMapper;
import Task6.ru.itis.shop.user.domain.User;
import Task6.ru.itis.shop.user.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private final DataSource dataSource;

    private final RowMapper<User> userRowMapper = row -> new User(
            row.getInt("id"),
            row.getString("name"),
            row.getString("email"),
            row.getString("password"),
            row.getString("profile_description")
    );

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (name, email, password, profile_description) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getProfileDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при сохранении пользователя", e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT id, name, email, password, profile_description FROM users WHERE email = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(userRowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при поиске по email", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Integer id) {
        String sql = "SELECT id, name, email, password, profile_description FROM users WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(userRowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при поиске по id", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateProfileByEmail(String email, String newDescription) {
        String sql = "UPDATE users SET profile_description = ? WHERE email = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newDescription);
            stmt.setString(2, email);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new IllegalStateException("Пользователь с email " + email + " не найден");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при обновлении описания", e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, email, password, profile_description FROM users";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(userRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при получении всех пользователей", e);
        }
        return users;
    }

    @Override
    public List<User> findAllByProfileDescription(String description) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, email, password, profile_description FROM users WHERE profile_description = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, description);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(userRowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Ошибка при поиске по описанию", e);
        }
        return users;
    }
}
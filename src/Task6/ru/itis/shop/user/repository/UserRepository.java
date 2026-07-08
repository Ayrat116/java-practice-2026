package Task6.ru.itis.shop.user.repository;

import Task6.ru.itis.shop.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Integer id);

    void updateProfileByEmail(String email, String newDescription);

    List<User> findAll();

    List<User> findAllByProfileDescription(String description);
}
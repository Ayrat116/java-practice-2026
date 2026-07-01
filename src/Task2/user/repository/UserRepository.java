package Task2.user.repository;

import Task2.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);

    boolean updateProfileByEmail(String email, String newDescription);
}

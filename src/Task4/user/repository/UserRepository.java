package Task4.user.repository;

import Task4.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);

    boolean updateProfileByEmail(String email, String newDescription);

    List<User> findAll();
}

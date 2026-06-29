package Task1.user.repository;

import Task1.user.domain.User;

public interface UserRepository {

    void save(User user);

    User findById(String id);
}

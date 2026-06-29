package Task1.user.infrastructure.persistence;

import Task1.user.domain.User;
import Task1.user.repository.UserRepository;

public class UserDatabaseRepository implements UserRepository {

    @Override
    public void save(User user) {
        System.out.println("Сохраняем в базу данных...");
    }

    @Override
    public User findById(String id) {
        System.out.println("Получаем из базы данных...");
        return null;
    }
}

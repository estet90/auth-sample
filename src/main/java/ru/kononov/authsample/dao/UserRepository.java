package ru.kononov.authsample.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kononov.authsample.model.dao.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLoginAndPassword(String login, String password);

}

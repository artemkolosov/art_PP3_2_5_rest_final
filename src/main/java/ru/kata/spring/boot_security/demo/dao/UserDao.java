package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

@Repository
public interface UserDao {

    User findByUserEmail(String email);
    User findByUsername(String username);
    List<User> getUsers();
    User getUser(Integer id);
    void removeUser(Integer id);
    void addUser(User user);
    void updateUser(User user);
}

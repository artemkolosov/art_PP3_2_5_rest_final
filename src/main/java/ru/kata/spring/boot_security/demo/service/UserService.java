package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getUsers();
    User getUser(Integer id);
    void addUser(User user);
    void updateUser(User user);
    void removeUser(Integer id);
    User findByUserEmail(String email);

    User findByUsername(String username);
}

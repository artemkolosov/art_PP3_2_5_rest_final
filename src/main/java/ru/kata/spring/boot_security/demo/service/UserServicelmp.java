package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServicelmp implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServicelmp(UserDao userDao, @Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {

        return userDao.getUsers();

    }


    @Override
    public User getUser(Integer id) {
        return userDao.getUser(id);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        User userFromDB = userDao.findByUsername(user.getUsername());
        String pwd = userFromDB.getPassword();
        userDao.updateUser(user);

        if (user.getPassword() == "" || user.getPassword() == pwd) {
            user.setPassword(pwd);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        //userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void removeUser(Integer id) {
        userDao.removeUser(id);
    }

    @Override
    public User findByUserEmail(String email) {
        return userDao.findByUserEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
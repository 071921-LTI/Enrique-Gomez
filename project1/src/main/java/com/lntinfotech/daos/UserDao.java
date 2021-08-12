package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.models.User;

public interface UserDao {
    User getUserById(int id);
    User getUserByUsername(String username);
    List<User> getEmployees();
    User addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);

}

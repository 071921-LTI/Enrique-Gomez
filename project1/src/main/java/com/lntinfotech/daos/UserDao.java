package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.exceptions.UserNotFoundException;
import com.lntinfotech.models.User;

public interface UserDao {
    User getUserById(int id) throws UserNotFoundException;
    User getUserByUsername(String username) throws UserNotFoundException;
    List<User> getUsers();
    int addUser(User user);
    int updateUser(User user) throws UserNotFoundException;
    int deleteUser(int id) throws UserNotFoundException;

}

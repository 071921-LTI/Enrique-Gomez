package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.exceptions.UserNotFoundException;
import com.lntinfotech.models.User;

public interface UserDao {

    public abstract User getUserById(int id) throws UserNotFoundException;
    public abstract User getUserByUsername(String username) throws UserNotFoundException;
    public abstract List<User> getAllUsers();
    public abstract boolean addUser(User user);
    public abstract int updateUser(User user);
    public abstract int deleteUser(String username);
}
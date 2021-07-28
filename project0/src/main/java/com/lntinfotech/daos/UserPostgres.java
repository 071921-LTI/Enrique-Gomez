package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.exceptions.UserNotFoundException;
import com.lntinfotech.models.User;

public class UserPostgres implements UserDao {

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addUser(User user) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int updateUser(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteUser(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return null;
    }
    
}

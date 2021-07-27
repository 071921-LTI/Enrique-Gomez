package com.lntinfotech.daos;

import com.lntinfotech.exceptions.UserNotFoundException;
import com.lntinfotech.models.User;

public class UserCollection implements UserDao {

    @Override
    public User getUser(String username) throws UserNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addUser(User user) {
        // TODO Auto-generated method stub
        return false;
    }
    
}

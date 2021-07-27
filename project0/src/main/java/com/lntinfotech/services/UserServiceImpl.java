package com.lntinfotech.services;

import com.lntinfotech.daos.UserCollection;
import com.lntinfotech.daos.UserDao;
import com.lntinfotech.exceptions.UserNotFoundException;
import com.lntinfotech.models.User;

public class UserServiceImpl implements UserService {

    private UserDao ud = new UserCollection();
    
    @Override
    public User getUser(String username) throws UserNotFoundException {
        // return ud.getUser(username);

        return new User("ezemog1996", "gomez1996", "customer");
    }

    @Override
    public boolean addUser(User user) {
        return ud.addUser(user);
    }
    
}

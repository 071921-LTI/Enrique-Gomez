package com.lntinfotech.services;

import com.lntinfotech.daos.UserPostgres;
import com.lntinfotech.daos.UserDao;
import com.lntinfotech.exceptions.UserNotFoundException;
import com.lntinfotech.models.User;

public class AuthServiceImpl implements AuthService {

    private UserDao ud = new UserPostgres();

    @Override
    public User login(User user) {
        
        try {
            User existingUser = ud.getUserByUsername(user.getUsername());

            if (existingUser.getPassword().equals(user.getPassword())) {
                return existingUser;
            } else {
                return null;
            }
        } catch(UserNotFoundException e) {
            return null;
        } catch(NullPointerException e) {
            return null;
        }

    }

    @Override
    public boolean register(User user) {
        try {
            User existingUser = ud.getUserByUsername(user.getUsername());

            if (existingUser != null) {
                return false;
            } else {
                return true;
            }
        } catch(UserNotFoundException e) {
            return true;
        }
    }
    
}

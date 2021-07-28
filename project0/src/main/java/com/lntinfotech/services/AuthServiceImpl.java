package com.lntinfotech.services;

import com.lntinfotech.daos.UserPostgres;
import com.lntinfotech.daos.UserDao;
import com.lntinfotech.exceptions.UserNotFoundException;
import com.lntinfotech.models.User;

public class AuthServiceImpl implements AuthService {

    private UserDao ud = new UserPostgres();

    @Override
    public boolean login(User user) {
        
        // try {
        //     User existingUser = ud.getUser(user.getUsername());

        //     if (existingUser.getPassword().equals(user.getPassword())) {
        //         return true;
        //     } else {
        //         return false;
        //     }
        // } catch(UserNotFoundException e) {
        //     return false;
        // }

        return false;
    }
    
}

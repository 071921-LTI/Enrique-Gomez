package com.lntinfotech.services;

import com.lntinfotech.daos.UserDao;
import com.lntinfotech.daos.UserHibernate;
import com.lntinfotech.models.User;

public class AuthServiceImpl implements AuthService {

    UserDao userDao = new UserHibernate();
    
    @Override
    public User login(User user) {
        User checkingUser = userDao.getUserByUsername(user.getUsername());

        if (user.getPassword().equals(checkingUser.getPassword())) {
            return checkingUser;
        } else {
            return null;
        }
    }

    @Override
    public User register(User user) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

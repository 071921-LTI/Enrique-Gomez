package com.lntinfotech.services;

import com.lntinfotech.daos.UserDao;
import com.lntinfotech.daos.UserHibernate;
import com.lntinfotech.models.User;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserHibernate();

    @Override
    public boolean updateUserInfo(User user) {
        userDao.updateUser(user);
        return true;
    }
    
}

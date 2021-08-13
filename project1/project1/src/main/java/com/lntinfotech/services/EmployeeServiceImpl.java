package com.lntinfotech.services;

import java.util.List;

import com.lntinfotech.daos.UserDao;
import com.lntinfotech.daos.UserHibernate;
import com.lntinfotech.models.User;

public class EmployeeServiceImpl implements EmployeeService {

    UserDao userDao = new UserHibernate();
    
    @Override
    public User getEmployeeById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllEmployees() {
        return userDao.getEmployees();
    }
    
}

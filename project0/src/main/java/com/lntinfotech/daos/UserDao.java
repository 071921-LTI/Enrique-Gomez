package com.lntinfotech.daos;

import com.lntinfotech.exceptions.UserNotFoundException;
import com.lntinfotech.models.User;

public interface UserDao {
    public abstract User getUser(String username) throws UserNotFoundException;
    public abstract boolean addUser(User user);
}
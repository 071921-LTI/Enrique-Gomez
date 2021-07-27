package com.lntinfotech.services;

import com.lntinfotech.exceptions.UserNotFoundException;
import com.lntinfotech.models.User;

public interface UserService {
    public abstract User getUser(String username) throws UserNotFoundException;
    public abstract boolean addUser(User user);
}

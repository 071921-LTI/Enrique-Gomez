package com.lntinfotech.services;

import com.lntinfotech.models.User;

public interface AuthService {
    public abstract User login(User user);
    public abstract boolean register(User user);
}

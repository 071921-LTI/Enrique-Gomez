package com.lntinfotech.services;

import com.lntinfotech.models.User;

public interface AuthService {
    public abstract boolean login(User user);
}

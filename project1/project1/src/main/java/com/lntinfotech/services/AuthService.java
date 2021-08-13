package com.lntinfotech.services;

import com.lntinfotech.models.User;

public interface AuthService {
    User login(User user);
    User register(User user);
}

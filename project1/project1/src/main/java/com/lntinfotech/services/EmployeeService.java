package com.lntinfotech.services;

import java.util.List;

import com.lntinfotech.models.User;

public interface EmployeeService {
    User getEmployeeById(int id);
    List<User> getAllEmployees();
}

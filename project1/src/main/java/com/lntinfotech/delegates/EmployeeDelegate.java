package com.lntinfotech.delegates;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lntinfotech.models.User;
import com.lntinfotech.services.EmployeeService;
import com.lntinfotech.services.EmployeeServiceImpl;
import com.lntinfotech.services.UserService;
import com.lntinfotech.services.UserServiceImpl;

public class EmployeeDelegate implements Delegatable {

    private EmployeeService employeeService = new EmployeeServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public void process(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        String method = rq.getMethod();

        switch (method) {
            case "GET": handleGet(rq, rs);
                break;
            case "POST": handlePost(rq, rs);
                break;
            case "PUT": handlePut(rq, rs);
                break;
            case "DELETE": handleDelete(rq, rs);
                break;
            default: rs.sendError(404);
        }
    }

    @Override
    public void handleGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        String auth = rq.getHeader("Authorization");
        String userId = auth.split(":")[0];
        String role = auth.split(":")[1];

        if (role.equals("Employee")) {
            User employee = employeeService.getEmployeeById(Integer.valueOf(userId));
            try (PrintWriter pw = rs.getWriter()) {
                pw.write(new ObjectMapper().writeValueAsString(employee));
            }
        } else if (role.equals("Manager")) {
            String employeeId = rq.getAttribute("pathNext").toString();

            if (employeeId != "") {
                User employee = employeeService.getEmployeeById(Integer.valueOf(employeeId));

                if (employee != null) {
                    try (PrintWriter pw = rs.getWriter()) {
                        pw.write(new ObjectMapper().writeValueAsString(employee));
                    }
                } else {
                    rs.sendError(404);
                }
            } else {
                List<User> employees = employeeService.getAllEmployees();
                try (PrintWriter pw = rs.getWriter()) {
                    pw.write(new ObjectMapper().writeValueAsString(employees));
                }
            }
            
        }
        
    }

    @Override
    public void handlePost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handlePut(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        InputStream info = rq.getInputStream();

        User user = new ObjectMapper().readValue(info, User.class);

        boolean success = userService.updateUserInfo(user);

        if (success) {
            rs.setStatus(200);
        } else {
            rs.sendError(400);
        }
        
    }

    @Override
    public void handleDelete(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
    }
    
}

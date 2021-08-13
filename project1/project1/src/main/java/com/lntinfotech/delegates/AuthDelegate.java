package com.lntinfotech.delegates;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lntinfotech.models.User;
import com.lntinfotech.services.AuthService;
import com.lntinfotech.services.AuthServiceImpl;

public class AuthDelegate implements Delegatable {

    private AuthService authService = new AuthServiceImpl();

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
        System.out.println("it hit!");
        InputStream info = rq.getInputStream();

        User credentials = new ObjectMapper().readValue(info, User.class);

        User loggedInUser = authService.login(credentials);

        if (loggedInUser == null) {
            rs.sendError(404);
        } else {
            rs.setHeader("Authorization", loggedInUser.getId() + ":" + loggedInUser.getRole().getRole());

            rs.setStatus(200);
        }
        
    }

    @Override
    public void handlePost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        
    }

    @Override
    public void handlePut(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handleDelete(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
    }
    
}

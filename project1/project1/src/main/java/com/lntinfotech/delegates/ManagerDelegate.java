package com.lntinfotech.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerDelegate implements Delegatable {

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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handlePost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
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

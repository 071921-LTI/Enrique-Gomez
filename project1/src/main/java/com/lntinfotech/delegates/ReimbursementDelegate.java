package com.lntinfotech.delegates;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lntinfotech.models.Reimbursement;
import com.lntinfotech.services.ReimbursementService;
import com.lntinfotech.services.ReimbursementServiceImpl;

public class ReimbursementDelegate implements Delegatable {

    private ReimbursementService reimbService = new ReimbursementServiceImpl();

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
        String status = rq.getAttribute("pathNext").toString();
        String auth = rq.getHeader("Authorization");
        
        if (auth == null || auth.replaceAll("\\s", "").equals("")) {
            rs.sendError(401);
        } else {
            String[] authSplit = auth.split(":");
            int id = Integer.parseInt(authSplit[0]);
            String role = authSplit[1];
            if (role.equals("Employee")) {
                switch (status) {
                    case "pending": System.out.println("pending requests");
                        break;
                    case "resolved": System.out.println("resolved requests");
                        break;
                    default: rs.sendError(404);
                }
            } else if (role.equals("Manager")) {
                switch (status) {
                    case "pending": System.out.println("pending requests");
                        break;
                    case "resolved": System.out.println("resolved requests");
                        break;
                    default: rs.sendError(404);
                }
            } else {
                rs.sendError(403);
            }
        }
        
    }

    @Override
    public void handlePost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        InputStream info = rq.getInputStream();

        Reimbursement reimbursement = new ObjectMapper().readValue(info, Reimbursement.class);

        Reimbursement submittedReimbursement = reimbService.submitReimbursementRequest(reimbursement);

        if (submittedReimbursement == null) {
            rs.sendError(404);
        } else {
            rs.setStatus(201);
        }
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

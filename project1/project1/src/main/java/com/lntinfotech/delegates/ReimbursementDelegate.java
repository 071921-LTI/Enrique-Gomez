package com.lntinfotech.delegates;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lntinfotech.models.Reimbursement;
import com.lntinfotech.models.User;
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
            List<Reimbursement> reimbursements = null;
            if (role.equals("Employee")) {
                switch (status) {
                    case "pending": 
                        reimbursements = reimbService.getPendingReimbursementsByEmployeeId(id);
                        try (PrintWriter pw = rs.getWriter()) {
                            pw.write(new ObjectMapper().writeValueAsString(reimbursements));
                        }
                        break;
                    case "resolved":
                        reimbursements = reimbService.getResolvedReimbursementsByEmployeeId(id);
                        try (PrintWriter pw = rs.getWriter()) {
                            pw.write(new ObjectMapper().writeValueAsString(reimbursements));
                        }
                        break;
                    case "":
                        reimbursements = reimbService.getRequestsByEmployeeId(id);
                        System.out.println(reimbursements.toString());
                        try (PrintWriter pw = rs.getWriter()) {
                            pw.write(new ObjectMapper().writeValueAsString(reimbursements));
                        }
                        break;
                    default: rs.sendError(404);
                }
            } else if (role.equals("Manager")) {
                switch (status) {
                    case "pending":
                        reimbursements = reimbService.getAllPendingRequests();
                        try (PrintWriter pw = rs.getWriter()) {
                            pw.write(new ObjectMapper().writeValueAsString(reimbursements));
                        }
                        break;
                    case "resolved":
                        reimbursements = reimbService.getAllResolvedRequests();
                        try (PrintWriter pw = rs.getWriter()) {
                            pw.write(new ObjectMapper().writeValueAsString(reimbursements));
                        }
                        break;
                    case "employee":
                        InputStream employeeStream = rq.getInputStream();
                        User employee = new ObjectMapper().readValue(employeeStream, User.class);
                        reimbursements = reimbService.getRequestsByEmployeeId(employee.getId());
                        try (PrintWriter pw = rs.getWriter()) {
                            pw.write(new ObjectMapper().writeValueAsString(reimbursements));
                        }
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

        String auth = rq.getHeader("Authorization");
        System.out.println(auth);
        int id = Integer.valueOf(auth.split(":")[0]);

        Reimbursement reimbursement = new ObjectMapper().readValue(info, Reimbursement.class);
        reimbursement.setAuthor(new User(id));

        Reimbursement submittedReimbursement = reimbService.submitReimbursementRequest(reimbursement);

        if (submittedReimbursement == null) {
            rs.sendError(404);
        } else {
            rs.setStatus(201);
        }
    }

    @Override
    public void handlePut(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        String auth = rq.getHeader("Authorization");
        String userId = auth.split(":")[0];
        String userRole = auth.split(":")[1];

        if (userRole.equals("Manager")) {
            InputStream info = rq.getInputStream();

            Reimbursement reimbursement = new ObjectMapper().readValue(info, Reimbursement.class);
            reimbursement.setResolver(new User(Integer.valueOf(userId)));
            boolean success = reimbService.resolveRequest(reimbursement);
            if (success) {
                rs.setStatus(200);
            } else {
                rs.sendError(400);
            }
        } else {
            rs.sendError(401);
        }
    }

    @Override
    public void handleDelete(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
    }
    
}

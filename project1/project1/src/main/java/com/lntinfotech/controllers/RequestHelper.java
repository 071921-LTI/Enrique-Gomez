package com.lntinfotech.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lntinfotech.delegates.AuthDelegate;
import com.lntinfotech.delegates.Delegatable;
import com.lntinfotech.delegates.EmployeeDelegate;
import com.lntinfotech.delegates.ReimbursementDelegate;

public class RequestHelper {

	private Delegatable authDelegate = new AuthDelegate();
	private Delegatable reimbDelegate = new ReimbursementDelegate();
	private Delegatable employeeDelegate = new EmployeeDelegate();
	
	public void process(HttpServletRequest rq, HttpServletResponse rs) throws IOException, ServletException {
		
		String path = rq.getPathInfo();
		
		if (path != null) {
			
			path = path.substring(1);
			
			if(path.indexOf("/") != -1) {
				String[] paths = path.split("/");

				path = paths[0];

				try {
					rq.setAttribute("pathNext", paths[1]);
				} catch (Exception e) {
					rq.setAttribute("pathNext", "");	
				}
				
			}
			
			switch(path) {
				case "authorize": authDelegate.process(rq, rs);
					break;
				case "reimbursement": reimbDelegate.process(rq, rs);
					break;
				case "employee": employeeDelegate.process(rq, rs);
					break;
			default:
				rs.sendError(400, "Path not supported:" + path);
			}
		} else {
			rs.sendError(400, "No path found.");
		}
	}
}
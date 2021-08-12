package com.lntinfotech.services;

import java.util.List;

import com.lntinfotech.models.Reimbursement;

public interface ReimbursementService {
    List<Reimbursement> getAllPendingRequests();
    List<Reimbursement> getAllResolvedRequests();
    List<Reimbursement> getRequestsByEmployeeId(int id);
    Reimbursement submitReimbursementRequest(Reimbursement reimbursement);
    List<Reimbursement> getPendingReimbursementsByEmployeeId(int id);
    List<Reimbursement> getResolvedReimbursementsByEmployeeId(int id);
    boolean resolveRequest(Reimbursement reimbursement);
}

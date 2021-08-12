package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.models.Reimbursement;

public interface ReimbursementDao {
    List<Reimbursement> getAllReimbursements();
    List<Reimbursement> getAllReimbursementsByUserId(int userId);
    List<Reimbursement> getResolvedReimbursementsByUserId(int userId);
    List<Reimbursement> getPendingReimbursementsByUserId(int userId);
    Reimbursement addReimbursement(Reimbursement reimbursement);
    void updateReimbursement(Reimbursement reimbursement);
    void deleteReimbusement(Reimbursement reimbursement);
}

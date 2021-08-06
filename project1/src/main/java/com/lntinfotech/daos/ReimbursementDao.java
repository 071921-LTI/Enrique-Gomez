package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.exceptions.ReimbursementNotFoundException;
import com.lntinfotech.models.Reimbursement;

public interface ReimbursementDao {
    List<Reimbursement> getAllReimbursements();
    List<Reimbursement> getAllReimbursementsByUserId(int userId);
    List<Reimbursement> getResolvedReimbursementsByUserId(int userId);
    List<Reimbursement> getPendingReimbursementsByUserId(int userId);
    int addReimbursement(Reimbursement reimbursement);
    int updateReimbursement(Reimbursement reimbursement);
    int deleteReimbusement(int id) throws ReimbursementNotFoundException;
}

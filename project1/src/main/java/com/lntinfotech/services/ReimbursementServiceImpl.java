package com.lntinfotech.services;

import java.util.List;

import com.lntinfotech.daos.ReimbursementDao;
import com.lntinfotech.daos.ReimbursementHibernate;
import com.lntinfotech.models.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService{

    ReimbursementDao reimbDao = new ReimbursementHibernate();

    @Override
    public List<Reimbursement> getAllPendingRequests() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Reimbursement> getAllResolvedRequests() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Reimbursement> getRequestsByEmployeeId(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Reimbursement submitReimbursementRequest(Reimbursement reimbursement) {
        return reimbDao.addReimbursement(reimbursement);
    }

    @Override
    public List<Reimbursement> getPendingReimbursementsByEmployeeId(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Reimbursement> getResolvedReimbursementsByEmployeeId(int id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

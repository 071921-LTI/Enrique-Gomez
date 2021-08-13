package com.lntinfotech.services;

import java.util.List;

import com.lntinfotech.daos.ReimbursementDao;
import com.lntinfotech.daos.ReimbursementHibernate;
import com.lntinfotech.models.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService{

    ReimbursementDao reimbDao = new ReimbursementHibernate();

    @Override
    public List<Reimbursement> getAllPendingRequests() {
        return reimbDao.getAllPendingRequests();
    }

    @Override
    public List<Reimbursement> getAllResolvedRequests() {
        return reimbDao.getAllResolvedRequests();
    }

    @Override
    public List<Reimbursement> getRequestsByEmployeeId(int id) {
        return reimbDao.getAllReimbursementsByUserId(id);
    }

    @Override
    public Reimbursement submitReimbursementRequest(Reimbursement reimbursement) {
        return reimbDao.addReimbursement(reimbursement);
    }

    @Override
    public List<Reimbursement> getPendingReimbursementsByEmployeeId(int id) {
        return reimbDao.getPendingReimbursementsByUserId(id);
    }

    @Override
    public List<Reimbursement> getResolvedReimbursementsByEmployeeId(int id) {
        return reimbDao.getResolvedReimbursementsByUserId(id);
    }

    @Override
    public boolean resolveRequest(Reimbursement reimbursement) {
        Reimbursement newReimbursement = reimbDao.getReimbursementById(reimbursement.getId());
        newReimbursement.setStatus(reimbursement.getStatus());
        newReimbursement.setResolver(reimbursement.getResolver());
        reimbDao.updateReimbursement(newReimbursement);
        return true;
    }
    
}

package com.lntinfotech.daos;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.lntinfotech.models.Reimbursement;
import com.lntinfotech.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReimbursementHibernate implements ReimbursementDao {

    @Override
    public List<Reimbursement> getAllReimbursements() {
        List<Reimbursement> reimbursements = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            reimbursements = s.createQuery("from Reimbursement", Reimbursement.class).list();
        }

        return reimbursements;
    }

    @Override
    public List<Reimbursement> getAllReimbursementsByUserId(int userId) {
        List<Reimbursement> reimbursements = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
            Root<Reimbursement> root = cq.from(Reimbursement.class);
            
            Predicate predicateForUserId = cb.equal(root.get("author"), userId);

            cq.select(root).where(predicateForUserId);

            reimbursements = s.createQuery(cq).getResultList();
        }

        return reimbursements;
    }

    @Override
    public List<Reimbursement> getResolvedReimbursementsByUserId(int userId) {
        List<Reimbursement> reimbursements = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
            Root<Reimbursement> root = cq.from(Reimbursement.class);
            
            Predicate predicateForId = cb.equal(root.get("author"), userId);
            Predicate predicateForApproved = cb.equal(root.get("status"), 2);
            Predicate predicateForDenied = cb.equal(root.get("status"), 3);

            Predicate predicateForStatus = cb.or(predicateForApproved, predicateForDenied);

            Predicate finalPredicate = cb.and(predicateForId, predicateForStatus);

            cq.select(root).where(finalPredicate);

            reimbursements = s.createQuery(cq).getResultList();
        }

        return reimbursements;
    }

    @Override
    public List<Reimbursement> getPendingReimbursementsByUserId(int userId) {
        List<Reimbursement> reimbursements = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
            Root<Reimbursement> root = cq.from(Reimbursement.class);
            
            Predicate predicateForId = cb.equal(root.get("author"), userId);
            Predicate predicateForPending = cb.equal(root.get("status"), 1);

            Predicate finalPredicate = cb.and(predicateForId, predicateForPending);

            cq.select(root).where(finalPredicate);

            reimbursements = s.createQuery(cq).getResultList();
        }

        return reimbursements;
    }

    @Override
    public Reimbursement addReimbursement(Reimbursement reimbursement) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = s.beginTransaction();
            s.save(reimbursement);
            transaction.commit();
        }

        return reimbursement;
    }

    @Override
    public void updateReimbursement(Reimbursement reimbursement) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = s.beginTransaction();
            s.update(reimbursement);
            transaction.commit();
        }
    }

    @Override
    public void deleteReimbusement(Reimbursement reimbursement) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = s.beginTransaction();
            s.delete(reimbursement);
            transaction.commit();
        }
    }

    @Override
    public List<Reimbursement> getAllPendingRequests() {
        List<Reimbursement> reimbursements = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Reimbursement where status_id = :statusId";
            TypedQuery<Reimbursement> query = s.createQuery(hql, Reimbursement.class);
            query.setParameter("statusId", 1);
            reimbursements = query.getResultList();
        }

        return reimbursements;
    }

    @Override
    public List<Reimbursement> getAllResolvedRequests() {
        List<Reimbursement> reimbursements = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
            Root<Reimbursement> root = cq.from(Reimbursement.class);
            
            Predicate predicateForApproved = cb.equal(root.get("status"), 2);
            Predicate predicateForDenied = cb.equal(root.get("status"), 3);

            Predicate finalPredicate = cb.or(predicateForApproved, predicateForDenied);

            cq.select(root).where(finalPredicate);

            reimbursements = s.createQuery(cq).getResultList();
        }

        return reimbursements;
    }

    @Override
    public Reimbursement getReimbursementById(int id) {
        Reimbursement reimbursement = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Reimbursement where reimb_id = :id";
            TypedQuery<Reimbursement> query = s.createQuery(hql, Reimbursement.class);
            query.setParameter("id", id);
            reimbursement = query.getSingleResult();
        }

        return reimbursement;
    }
    
}

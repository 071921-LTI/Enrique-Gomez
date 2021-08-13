package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.models.Status;
import com.lntinfotech.util.HibernateUtil;

import org.hibernate.Session;

public class StatusHibernate implements StatusDao {

    @Override
    public List<Status> getAllStatuses() {
        List<Status> statuses = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            statuses = s.createQuery("from Status", Status.class).list();

        }

        return statuses;
    }
    
}

package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.models.Role;
import com.lntinfotech.util.HibernateUtil;

import org.hibernate.Session;

public class RoleHibernate implements RoleDao {

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            roles = s.createQuery("from roles", Role.class).list();
        }

        return roles;
    }
    
}

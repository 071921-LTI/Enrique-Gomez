package com.lntinfotech.daos;

import java.util.List;

import com.lntinfotech.models.Type;
import com.lntinfotech.util.HibernateUtil;

import org.hibernate.Session;

public class TypeHibernate implements TypeDao {

    @Override
    public List<Type> getAllTypes() {
        List<Type> types = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            types = s.createQuery("from Type", Type.class).list();
        }

        return types;
    }
    
}

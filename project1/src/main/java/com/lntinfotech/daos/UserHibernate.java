package com.lntinfotech.daos;

import java.util.List;

import javax.persistence.TypedQuery;

import com.lntinfotech.models.User;
import com.lntinfotech.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserHibernate implements UserDao {

    @Override
    public User getUserById(int id) {
        User user = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            user = s.get(User.class, id);
        }

        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from User where username = :username";
            TypedQuery<User> query = s.createQuery(hql, User.class);
            query.setParameter("username", username);
            user = query.getSingleResult();
        }

        return user;
    }

    @Override
    public List<User> getEmployees() {
        List<User> users = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from User where role = :role";
            TypedQuery<User> query = s.createQuery(hql, User.class);
            query.setParameter("role", "Employee");
            users = query.getResultList();
        }

        return users;
    }

    @Override
    public User addUser(User user) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = s.beginTransaction();
            s.save(user);
            transaction.commit();
        }

        return user;
    }

    @Override
    public void updateUser(User user) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = s.beginTransaction();
            s.update(user);
            transaction.commit();
        }
    }

    @Override
    public void deleteUser(User user) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = s.beginTransaction();
            s.delete(user);
            transaction.commit();
        }
    }
    
}

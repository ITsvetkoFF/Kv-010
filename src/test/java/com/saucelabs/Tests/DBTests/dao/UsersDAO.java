package com.saucelabs.Tests.DBTests.dao;

import com.saucelabs.Tests.DBTests.entities.Users;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

import java.util.List;

public class UsersDAO extends MainDAO{

    public Users findById(Integer id) {
        Users user = (Users) getSession().get(Users.class, id);
        closeSession();
        return user;
    }

    public Users findUserByEmail(String email) {
        Users user = null;
        List<Users> users = null;
        try {
            System.out.println("--------"+getSession().isDirty()+"--------");
            Query query = getSession().createQuery("from Users where Email = :email");
            query.setParameter("email", email);
            //getSession().setCacheMode(CacheMode.IGNORE);
            users = query.list();
        } catch (HibernateException e) {
            System.out.println("--------HIBERNATE EXCEPTION-------");
            e.printStackTrace();
        }
        finally {
            closeSession();
        }

        if (!users.isEmpty()) {
            user = users.get(0);
        } else {
            user = null;
        }
        return user;
    }

    public void deleteUserByEmail(String email) {
        Transaction transaction = getSession().beginTransaction();
        try {
            Query query = getSession().createQuery("delete Users where Email = :email");
            query.setParameter("email", email);
            query.executeUpdate();
            transaction.commit();
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        }
        closeSession();
    }

    public void deleteUser(Users user) {
        getSession().delete(user);
        closeSession();
    }


}


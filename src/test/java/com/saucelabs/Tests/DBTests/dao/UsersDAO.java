package com.saucelabs.Tests.DBTests.dao;

import com.saucelabs.Tests.DBTests.entities.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import utility.HibernateUtil;

import java.util.List;

public class UsersDAO {

    private Session session = null;

    public Session getSession() {
        return session;
    }

    private void openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    private void closeSession() {
        session = HibernateUtil.getSessionFactory().close();
    }

    public Users findById(Integer id) {
        openSession();
        Users user = (Users) getSession().get(Users.class, id);
        closeSession();
        return user;
    }

    public List<Users> findByEmail(String email) {
        openSession();
        Query query = session.createQuery("from Users where email = :email");
        query.setParameter("email", email);
        List<Users> users = query.list();
        closeSession();
        return users;
    }
}


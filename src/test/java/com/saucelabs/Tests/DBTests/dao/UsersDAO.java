package com.saucelabs.Tests.DBTests.dao;

import com.saucelabs.Tests.DBTests.entities.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import utility.HibernateUtil;

import java.util.List;

public class UsersDAO extends MainDAO{

    public Users findById(Integer id) {
        Users user = (Users) getSession().get(Users.class, id);
        closeSession();
        return user;
    }

    public Users findUserByEmail(String email) {
        Users user = null;
        Query query = getSession().createQuery("from Users where email = :email");
        query.setParameter("email", email);
        List<Users> users = query.list();
        closeSession();
        if (!users.isEmpty()) {
            user = users.get(0);
        }
        return user;
    }

    public void deleteUserByEmail(String email) {
        Query query = getSession().createQuery("delete Users where email = :email");
        query.setParameter("email", email);
        query.executeUpdate();

    }


}


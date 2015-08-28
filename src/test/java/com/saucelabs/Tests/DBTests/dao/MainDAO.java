package com.saucelabs.Tests.DBTests.dao;


import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import utility.HibernateUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Base abstract Class for all DAO classes. It needs for working with a Session.
 */
abstract class MainDAO {
    private Session session = null;

    /**
     *
     * @return Opened Session.
     */
    public Session getSession()
    {
        if (session == null) {
            openSession();
        }
        return session;
    }

    private void openSession() {
        session = HibernateUtil.getSessionFactory().openSession();

    }

    protected final void closeSession() {
        session.close();
//        if (session.isOpen()){
//            Connection c = session.close();
//            try {
//                System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbb"+c.isClosed());
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

    }
}

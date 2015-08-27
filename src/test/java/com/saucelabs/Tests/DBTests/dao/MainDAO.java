package com.saucelabs.Tests.DBTests.dao;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import utility.HibernateUtil;

import java.sql.SQLException;

/**
 * Base abstract Class for all DAO classes. It needs for working with a Session.
 */
abstract class MainDAO {
    private StatelessSession session = null;

    /**
     *
     * @return Opened Session.
     */
    public StatelessSession getSession()
    {
        if (session == null) {
            openSession();
        }
        return session;
    }

    private void openSession() {
        session = HibernateUtil.getSessionFactory().openStatelessSession();

    }

    protected final void closeSession() {
        //session.clear();
        session.close();
    }
}

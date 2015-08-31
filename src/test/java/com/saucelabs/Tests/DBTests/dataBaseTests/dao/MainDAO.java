package com.saucelabs.Tests.DBTests.dataBaseTests.dao;


import org.hibernate.Session;
import utility.HibernateUtil;


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
        if (session == null || !session.isOpen()) {
            openSession();
        }
        return session;
    }

    private void openSession() {
        session = HibernateUtil.getSessionFactory().openSession();

    }

    protected final void closeSession() {
        session.close();

    }
}

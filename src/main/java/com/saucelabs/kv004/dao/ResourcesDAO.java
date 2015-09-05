package com.saucelabs.kv004.dao;

import com.saucelabs.kv004.entities.Resources;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by odeortc on 01.09.2015.
 */
public class ResourcesDAO extends MainDAO {

    public Resources findById(Integer id) {
        Resources resource = (Resources) getSession().get(Resources.class, id);
        closeSession();
        return resource;
    }

    public Resources findResourceByTitle(String title) {
        Resources resource = null;
        List<Resources> Resources = null;
        try {
            Query query = getSession().createQuery("from Resources where Title = :title");
            query.setParameter("title", title);
            Resources = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        finally {
            closeSession();
        }

        if (!Resources.isEmpty()) {
            resource = Resources.get(0);
        } else {
            resource = null;
        }
        return resource;
    }
}

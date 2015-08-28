package com.saucelabs.Tests.DBTests.dao;

import com.saucelabs.Tests.DBTests.entities.Problems;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import java.util.List;

/**
 * Created by acidroed on 22.08.2015.
 */
public class ProblemsDAO extends MainDAO {

    public Problems findById(Integer id) {
        Problems problem = (Problems) getSession().get(Problems.class, id);
        closeSession();
        return problem;
    }

    public Problems findProblemByTitle(String title) {
        Problems problem = null;
        List<Problems> problems = null;
        try {
            getSession().setCacheMode(CacheMode.IGNORE);
            getSession().clear();
            Query query = getSession().createQuery("from Problems where Title = :title");
            query.setParameter("title", title);
            problems = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            closeSession();
        }
        if (!problems.isEmpty()) {
            problem = problems.get(0);
            System.out.println("Problemmmmmmmmmmmmm : " + problem.getTitle());
        } else {
            problem = null;
        }

        return problem;
    }
}

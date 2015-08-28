package com.saucelabs.Tests.DBTests.dao;

import com.saucelabs.Tests.DBTests.entities.Problems;
import com.saucelabs.Tests.DBTests.entities.Users;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.sql.Connection;
import java.sql.SQLException;
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
            System.out.println("--------"+getSession().isDirty()+"--------");
            Query query = getSession().createQuery("from Problems where Title = :title");
            query.setParameter("title", title);
            //getSession().setCacheMode(CacheMode.IGNORE);
            problems = query.list();
        } catch (HibernateException e) {
            System.out.println("--------HIBERNATE EXCEPTION-------");
            e.printStackTrace();
        }
        finally {
            closeSession();
        }
        System.out.println(problems.size());
                if (!problems.isEmpty()) {
            problem = problems.get(0);
        } else {
            problem = null;
        }
        return problem;
    }
}

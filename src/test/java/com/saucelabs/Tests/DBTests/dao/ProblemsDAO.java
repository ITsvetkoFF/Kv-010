package com.saucelabs.Tests.DBTests.dao;

import com.saucelabs.Tests.DBTests.entities.Problems;
import com.saucelabs.Tests.DBTests.entities.Users;
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
        System.out.println("================================================");
        Query query = getSession().createQuery("from Problems where Title = :title");
        query.setParameter("title", title);
        List<Problems> problems = query.list();
        System.out.println("================+++++++++++++++++++===============================");
        closeSession();
        if (!problems.isEmpty()) {
            problem = problems.get(0);
        } else {
            problem = null;
        }
        return problem;
    }
}

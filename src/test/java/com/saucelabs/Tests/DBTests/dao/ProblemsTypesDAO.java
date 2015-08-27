package com.saucelabs.Tests.DBTests.dao;

import com.saucelabs.Tests.DBTests.entities.ProblemTypes;
import com.saucelabs.Tests.DBTests.entities.Users;
import org.hibernate.Query;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by acidroed on 25.08.2015.
 */
public class ProblemsTypesDAO extends MainDAO {

    public ProblemTypes findById(Integer id) {
        ProblemTypes problemType = (ProblemTypes) getSession().get(ProblemTypes.class, id);
        closeSession();
        return problemType;
    }

    public ProblemTypes findByProblemTypeName(String type) {
        ProblemTypes problemTypes = null;
        Query query = getSession().createQuery("from ProblemTypes where Type = :type");
        query.setParameter("type", type);
        List<ProblemTypes> problemTypesList = query.list();
        //closeSession();
        if (!problemTypesList.isEmpty()) {
            problemTypes = problemTypesList.get(0);
        } else {
            problemTypes = null;
        }
        return problemTypes;
    }

}

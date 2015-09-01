package com.saucelabs.kv004.dao;

import com.saucelabs.kv004.entities.Activities;
import org.hibernate.Query;

import java.util.List;


/**
 * Created by acidroed on 25.08.2015.
 */
public class ActivitiesDAO extends MainDAO {

    public Activities findById(Integer id) {
        Activities activity = (Activities) getSession().get(Activities.class, id);
        closeSession();
        return activity;
    }

    public Activities findByUserIdAndProblemID(int userID, int problemID) {

        Activities activities = null;
        Query query = getSession().createQuery("from Activities where Users_Id = :userID and Problems_Id = :problemID");
        query.setParameter("userID", userID);
        query.setParameter("problemID", problemID);
        List<Activities> problemTypesList = query.list();
        closeSession();
        if (!problemTypesList.isEmpty()) {
            activities = problemTypesList.get(0);
        } else {
            activities = null;
        }
        return activities;
    }
}

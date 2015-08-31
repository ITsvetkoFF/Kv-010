package com.saucelabs.Tests.DBTests.dataBaseTests.dao;

import com.saucelabs.Tests.DBTests.dataBaseTests.entities.ActivityTypes;

/**
 * Created by acidroed on 25.08.2015.
 */
public class ActivityTypesDAO extends MainDAO {

    public ActivityTypes findById(Integer id) {
        ActivityTypes activityType = (ActivityTypes) getSession().get(ActivityTypes.class, id);
        closeSession();
        return activityType;

    }
}

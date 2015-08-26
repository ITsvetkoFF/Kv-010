package com.saucelabs.Tests.DBTests.dao;

import com.saucelabs.Tests.DBTests.entities.UserRoles;
import com.saucelabs.Tests.DBTests.entities.Users;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by odeortc on 21.08.2015.
 */
public class UserRolesDAO extends MainDAO{

    public UserRoles findById(Integer id) {
        UserRoles userRoles = (UserRoles) getSession().get(UserRoles.class, id);
        closeSession();
        return userRoles;
    }

    public UserRoles findUserRolesByRole(String role) {
        UserRoles userRole = null;
        Query query = getSession().createQuery("from UserRoles where Role = :role");
        query.setParameter("role", role);
        List<UserRoles> roles = query.list();
        closeSession();
        if (!roles.isEmpty()) {
            userRole = roles.get(0);
        }
        return userRole;
    }
}

package com.saucelabs.kv008.dao;

import com.saucelabs.kv008.entities.ResourceEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Den on 04.09.2015.
 */
public class ResourceDao  extends BaseDao<ResourceEntity,String>{
    @Override
    protected String getQuery(QueryType queryType) {
        return null;
    }

    @Override
    protected List<ResourceEntity> parseResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, ResourceEntity resourceEntity) throws SQLException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, ResourceEntity resourceEntity) throws SQLException {

    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, ResourceEntity resourceEntity) throws SQLException {

    }
}

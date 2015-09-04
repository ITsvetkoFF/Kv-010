package com.saucelabs.kv008.dao;

import com.saucelabs.kv008.entities.PhotoEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Den on 04.09.2015.
 */
public class PhotoDao extends BaseDao<PhotoEntity, Integer> {
    @Override
    protected String getQuery(QueryType queryType) {
        return null;
    }

    @Override
    protected List<PhotoEntity> parseResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, PhotoEntity photoEntity) throws SQLException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, PhotoEntity photoEntity) throws SQLException {

    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, PhotoEntity photoEntity) throws SQLException {

    }
}

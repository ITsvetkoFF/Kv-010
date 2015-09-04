package com.saucelabs.kv008.dao;

import com.saucelabs.kv008.entities.ProblemEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Den on 04.09.2015.
 */
public class ProblemDao extends BaseDao<ProblemEntity,Integer> {
    @Override
    protected String getQuery(QueryType queryType) {
        return null;
    }

    @Override
    protected List<ProblemEntity> parseResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, ProblemEntity problemEntity) throws SQLException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, ProblemEntity problemEntity) throws SQLException {

    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, ProblemEntity problemEntity) throws SQLException {

    }
}

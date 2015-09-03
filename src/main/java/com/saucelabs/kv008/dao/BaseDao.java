package com.saucelabs.kv008.dao;




import com.saucelabs.kv008.util.ConnectorDB;

import java.io.Serializable;
import java.sql.*;
import java.util.List;

/**
 * Created by dmakstc on 01.09.2015.
 */
public abstract class BaseDao<T, Pk extends Serializable> implements GenericDao<T, Pk> {
    enum QueryType{
        GET_ALL,
        CREATE,
        READ,
        UPDATE,
        DELETE
    }



    @Override
    final public List<T> getAll() {
        List<T> resultList = null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(getQuery(QueryType.GET_ALL))
        ) {
            ResultSet rs = statement.executeQuery();
            resultList = parseResultSet(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    final public T readByKey(Pk pk) {
        T result = null;
        List<T> resultList;


        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(getQuery( QueryType.READ))
        ) {
            statement.setObject(1, pk);
            ResultSet rs = statement.executeQuery();
            resultList = parseResultSet(rs);

            if (resultList.size() == 1) {
                result = resultList.get(0);
            } else if (resultList.size() > 1) {
                throw new RuntimeException("Not unique ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    final public Pk create(T t) {
        Pk result = null;
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(getQuery( QueryType.CREATE), Statement.RETURN_GENERATED_KEYS)
        ) {
            prepareStatementForCreate(statement,t);
            if(statement.executeUpdate()==1){
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()){
                    result = (Pk) generatedKeys.getObject(1);
                }


            }else {
                throw new RuntimeException("create");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    final public void update(T t) {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(getQuery(QueryType.UPDATE))

        ) {
            prepareStatementForUpdate(statement,t);
            if(statement.executeUpdate()>1){
                throw new RuntimeException("update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    final public void delete(T t) {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(getQuery(QueryType.DELETE))
        ) {
            prepareStatementForDelete(statement, t);
            if(statement.executeUpdate()>1){
                throw new RuntimeException("delete");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    final protected Connection getConnection() throws SQLException {

        return ConnectorDB.getConnection();
    }

    abstract protected String getQuery( QueryType queryType);

    abstract protected List<T> parseResultSet(ResultSet rs) throws SQLException;

    protected abstract void prepareStatementForCreate(PreparedStatement statement, T t) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T t) throws SQLException;

    protected abstract void prepareStatementForDelete(PreparedStatement statement, T t) throws SQLException;


}

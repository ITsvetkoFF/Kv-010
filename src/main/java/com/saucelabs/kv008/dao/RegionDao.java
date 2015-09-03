package com.saucelabs.kv008.dao;


import com.saucelabs.kv008.entities.RegionEntity;
import org.postgis.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class RegionDao extends BaseDao<RegionEntity,Integer> {


    @Override
    protected String getQuery(QueryType queryType) {
        String res;
        switch (queryType){
            case GET_ALL:
                res = "Select * from regions";
                break;
            case  CREATE:
                res = "Insert into regions(name,location) values(?,?)";
                break;
            case  READ:
                res = "Select * from regions where id = ?";
                break;
            case  UPDATE:
                res = "Update regions set name= ? ,location = ? where id  = ?";
                break;
            case  DELETE:
                res = "Delete from regions where id = ?";
                break;
            default:
                throw new RuntimeException("aaaaa");


        }
        return res;
    }

    @Override
    protected List<RegionEntity> parseResultSet(ResultSet rs) throws SQLException {
        List<RegionEntity> resList = new LinkedList<>();
        while (rs.next()){
            RegionEntity tmp = new RegionEntity();
            tmp.setId(rs.getInt("id"));
            tmp.setName(rs.getString("name"));
            tmp.setLocation((PGgeometry)rs.getObject("location"));
            resList.add(tmp);
        }
        return resList;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, RegionEntity regionEntity) throws SQLException {
        statement.setString(1,regionEntity.getName());
        statement.setObject(2,regionEntity.getLocation());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, RegionEntity regionEntity) throws SQLException {
        statement.setString(1,regionEntity.getName());
        statement.setObject(2,regionEntity.getLocation());
        statement.setInt(3,regionEntity.getId());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, RegionEntity regionEntity) throws SQLException {
        statement.setInt(1,regionEntity.getId());
    }


}

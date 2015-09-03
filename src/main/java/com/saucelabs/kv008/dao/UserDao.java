package com.saucelabs.kv008.dao;


import com.saucelabs.kv008.entities.UserEntity;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dmakstc on 02.09.2015.
 */
public class UserDao extends BaseDao<UserEntity, Integer> {
    @Override
    protected String getQuery(QueryType queryType) {
        String res;
        switch (queryType){
            case GET_ALL:
                res = "Select * from users";
                break;
            case  CREATE:
                res = "Insert into users() values(?,?)";
                break;
            case  READ:
                res = "Select * from users where id = ?";
                break;
            case  UPDATE:
                res = "Update users set ___= ? ,___ = ? where id  = ?";
                break;
            case  DELETE:
                res = "Delete from users where id = ?";
                break;
            default:
                throw new RuntimeException("aaaaa");


        }
        return res;
    }

    @Override
    protected List<UserEntity> parseResultSet(ResultSet rs) throws SQLException {
        List<UserEntity> resList = new LinkedList<>();
        while (rs.next()) {
            UserEntity tmp = new UserEntity();
            tmp.setId(rs.getInt("id"));
            tmp.setFirst_name(rs.getString("first_name"));
            tmp.setLast_name(rs.getString("last_name"));
            tmp.setEmail(rs.getString("email"));
            tmp.setPassword(rs.getString("password"));
            tmp.setRegion_id(rs.getInt("region_id"));
            tmp.setGoogle_id(rs.getString("google_id"));
            tmp.setFacebook_id(rs.getString("facebook_id"));


            resList.add(tmp);
        }
        return resList;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, UserEntity userEntity) throws SQLException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UserEntity userEntity) throws SQLException {

    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, UserEntity userEntity) throws SQLException {
        statement.setInt(1,userEntity.getId());
    }
}

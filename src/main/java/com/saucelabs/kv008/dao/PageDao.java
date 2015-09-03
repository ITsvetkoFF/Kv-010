package com.saucelabs.kv008.dao;

import com.saucelabs.kv008.entities.PageEntity;
import com.saucelabs.kv008.entities.RegionEntity;
import org.postgis.PGgeometry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 03.09.2015.
 */
public class PageDao extends BaseDao<PageEntity,Integer>{

    public PageEntity readByAlias(String alias){
        PageEntity result = null;
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM pages WHERE alias=?");
        ) {
            statement.setString(1,alias);
            ResultSet rSet = statement.executeQuery();
            if(rSet.next()){
                result = new PageEntity();
                result.setId(rSet.getInt("id"));
                result.setAlias(rSet.getString("alias"));
                result.setTitle(rSet.getString("title"));
                result.setContent(rSet.getString("content"));
                result.setResource(rSet.getBoolean("is_resource"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected String getQuery(QueryType queryType) {
        String res;
        switch (queryType){
            case GET_ALL:
                res = "Select * from pages";
                break;
            case  CREATE:
                res = "Insert into pages(alias,title,content,is_resource) values(?,?,?,?)";
                break;
            case  READ:
                res = "Select * from pages where id = ?";
                break;
            case  UPDATE:
                res = "Update pages set alias= ? ,title = ?, content = ?, is_resource = ?  where id  = ?";
                break;
            case  DELETE:
                res = "Delete from pages where id = ?";
                break;
            default:
                throw new RuntimeException("aaaaa");


        }
        return res;
    }

    @Override
    protected List<PageEntity> parseResultSet(ResultSet rs) throws SQLException {
        List<PageEntity> resList = new LinkedList<>();
        while (rs.next()){
            PageEntity tmp = new PageEntity();
            tmp.setId(rs.getInt("id"));
            tmp.setAlias(rs.getString("alias"));
            tmp.setTitle(rs.getString("title"));
            tmp.setContent(rs.getString("content"));
            tmp.setResource(rs.getBoolean("is_resource"));

            resList.add(tmp);
        }
        return resList;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, PageEntity pageEntity) throws SQLException {
        statement.setString(1,pageEntity.getAlias());
        statement.setString(2,pageEntity.getTitle());
        statement.setString(3,pageEntity.getContent());
        statement.setBoolean(4,pageEntity.isResource());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, PageEntity pageEntity) throws SQLException {
        statement.setString(1,pageEntity.getAlias());
        statement.setString(2,pageEntity.getTitle());
        statement.setString(3,pageEntity.getContent());
        statement.setBoolean(4,pageEntity.isResource());
        statement.setInt(5,pageEntity.getId());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, PageEntity pageEntity) throws SQLException {
        statement.setInt(1,pageEntity.getId());
    }
}

package com.saucelabs.Tests.DAO;


import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nklimotc on 05.11.2014.
 */
public class ResourcesDAO extends BaseDAO {

    public static final String DB_URL_WITH_UTF_8 = "jdbc:mysql://localhost:3306/enviromap?useUnicode=true&characterEncoding=UTF-8";
    public static final String USER_NAME = "root";
    public static final String USER_PASSWORD = "root";

    public List<String> getAllAliases() throws SQLException, ClassNotFoundException {
        Statement statement = getConnection(DB_URL_WITH_UTF_8, USER_NAME, USER_PASSWORD).createStatement();
        ResultSet resultSet = statement.executeQuery("select * from resources;");
        List<String> result = new ArrayList<>();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("Title"));
            result.add(resultSet.getString("Title"));
        }
        statement.close();
        return result;
    }

    public void addResourceToDB(String alias, String title, String content, int isResource) throws SQLException, ClassNotFoundException {

        String query = "insert into resources (Alias, Title, Content, IsResource)"
                + " values (?, ?, ?, ?)";
        PreparedStatement preparedStmt = (PreparedStatement) getConnection(DB_URL_WITH_UTF_8, USER_NAME, USER_PASSWORD).prepareStatement(query);

        preparedStmt.setString  (1, alias);
        preparedStmt.setString  (2, title);
        preparedStmt.setString  (3, content);
        preparedStmt.setInt     (4, isResource);

        preparedStmt.executeUpdate();

        preparedStmt.close();
        }

    public String getResourceIdByTitle(String title) throws SQLException, ClassNotFoundException {

        String query = "select Id from resources where Title = ?;";
        PreparedStatement preparedStmt = (PreparedStatement) getConnection(DB_URL_WITH_UTF_8, USER_NAME, USER_PASSWORD).prepareStatement(query);
        String id = "";
        preparedStmt.setString  (1, title);

        ResultSet setWithId = preparedStmt.executeQuery();
        while (setWithId.next()) {
            id = setWithId.getString("Id");
        }

        preparedStmt.close();
        return id;
    }

    public String getResourceTitleById(String id) throws SQLException, ClassNotFoundException {

        String query = "select Title from resources where Id = ?;";
        PreparedStatement preparedStmt = (PreparedStatement) getConnection(DB_URL_WITH_UTF_8, USER_NAME, USER_PASSWORD).prepareStatement(query);
        String title = "";
        preparedStmt.setString  (1, id);

        ResultSet setWithId = preparedStmt.executeQuery();
        while (setWithId.next()) {
            title = setWithId.getString("Title");
        }

        preparedStmt.close();
        return title;
    }

    public List <String> deleteResourceFromDB(String title) throws SQLException, ClassNotFoundException {

        String query = "delete from resources where Title = ?;";
        PreparedStatement preparedStmt = (PreparedStatement) getConnection(DB_URL_WITH_UTF_8, USER_NAME, USER_PASSWORD).prepareStatement(query);
        List <String> result = new ArrayList<>();
        preparedStmt.setString  (1, title);

        preparedStmt.executeUpdate();

        preparedStmt.close();
        return result;
    }
}

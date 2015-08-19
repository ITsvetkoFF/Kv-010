package com.saucelabs.Tests.DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yermek on 05.11.2014.
 */
public class DeleteProblemDAO extends BaseDAO {
    public List<String> getPhotosLinksFromProblemsIs(int ProblemsId) throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Link FROM Photos WHERE Problems_Id = " +
                ProblemsId + ";");
        List<String> result = new ArrayList<>();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("Link"));
            result.add(resultSet.getString("Link"));
        }
        statement.close();
        return result;
    }

    public Map<String, String> getProblemById(int problemID) throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Problems WHERE Id = " + problemID + ";");
        Map<String, String> map = new HashMap<String, String>();

        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        int columnCount = rsMetaData.getColumnCount();

        System.out.println("");

        while (resultSet.next ())
        {
            for (int i = 0; i < columnCount; i++)
            {
                map.put(rsMetaData.getColumnName(i + 1), resultSet.getString (i + 1));
            }
        }
        statement.close();
        return map;
    }

    public ArrayList<Map<String, String>> getActivitiesByProblemId(int problemID) throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Activities WHERE Problems_Id = " + problemID + ";");
        ArrayList<Map<String, String>> map = new ArrayList<Map<String, String>>();

        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        int columnCount = rsMetaData.getColumnCount();

        System.out.println("");

        while (resultSet.next ())
        {
            Map<String, String> row = new HashMap<String, String>();
            for (int i = 0; i < columnCount; i++)
            {
                row.put(rsMetaData.getColumnName(i + 1), resultSet.getString(i + 1));
            }
            map.add(row);
        }
        statement.close();
        return map;
    }

    public ArrayList<Map<String, String>> getPhotosByProblemId(int problemID) throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Photos WHERE Problems_Id = " + problemID + ";");
        ArrayList<Map<String, String>> map = new ArrayList<Map<String, String>>();

        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        int columnCount = rsMetaData.getColumnCount();

        System.out.println("");

        while (resultSet.next ())
        {
            Map<String, String> row = new HashMap<String, String>();
            for (int i = 0; i < columnCount; i++)
            {
                row.put(rsMetaData.getColumnName(i + 1), resultSet.getString(i + 1));
            }
            map.add(row);
        }
        statement.close();
        return map;
    }
}

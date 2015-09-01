package com.saucelabs.archive.dataBaseTests.dao_old;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Olya on 11/11/14.
 */
public class DeleteUserDAO extends BaseDAO{
    static UserInfoDAO userInfoDB = new UserInfoDAO();
    public void deleteUser(String Email) throws SQLException, ClassNotFoundException {
        Statement statement = getConnection("jdbc:mysql://localhost:3306/enviromap","root", "root").createStatement();
        statement.executeUpdate("delete from users where Email = \"" + Email + "\"");
        statement.close();
    }

}

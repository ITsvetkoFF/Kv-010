package com.saucelabs.Tests.DAO;

import java.sql.*;

/**
 * Created by Olya on 11/8/14.
 */
public class CreateNewUserDAO extends BaseDAO {
    static UserInfoDAO userInfoDB = new UserInfoDAO();
    public void createUser(String Name, String Surname, String Email, String Password, String UserRole_Id) throws SQLException, ClassNotFoundException {
        Statement statement = getConnection("jdbc:mysql://localhost:3306/enviromap","root", "root").createStatement();
        statement.executeUpdate("insert into users (Name,Surname,Email,Password,UserRoles_Id) values (\"" + Name + "\",\"" + Surname + "\",\"" + Email + "\",\"" + userInfoDB.hmacSha1(Password,"qawvAsgn2GRtPww066ShB6cX79ZUAV7KTzXXvNIzkr0IlLnJ") + "\",\"" + UserRole_Id + "\");");
        statement.close();
    }
}
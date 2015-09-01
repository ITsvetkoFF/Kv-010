package com.saucelabs.archive.dataBaseTests.dao_old;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Olya on 11/8/14.
 */
public class UserInfoDAO extends BaseDAO{

    public Map getInfo(String DB_login, String DB_password, String DB_URL, String Email) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");//эта строка загружает драйвер DB

        Connection connection = DriverManager.getConnection(DB_URL, DB_login, DB_password);
        if (connection == null) {
            System.out.println("Нет соединения с БД!");
            System.exit(0);
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users where Email = \"" + Email + "\"");
        Map<String, String> Result = new HashMap<String,String>();
        while (resultSet.next()) {
            Result.put("Name", resultSet.getString("Name"));
            Result.put("Surname", resultSet.getString("Surname"));
            Result.put("Password",resultSet.getString("Password"));
            Result.put("UserRoles_Id", resultSet.getString("UserRoles_Id"));
        }
        statement.close();
        return Result;

    }

    public static String hmacSha1(String value, String key) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes());

            // Convert raw bytes to Hex
            byte[] hexBytes = new Hex().encode(rawHmac);

            //  Covert array of Hex bytes to a String
            return new String(hexBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

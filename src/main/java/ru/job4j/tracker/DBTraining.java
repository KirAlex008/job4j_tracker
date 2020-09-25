package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DBTraining {
    //private static final Logger Log = LoggerFactory.getLogger(DBTraining.class);

    public static void main(String[] args) {
        String url = "jdbc::postgresql://localhost:5432/java_a_from_z";
        String username = "postgres";
        String password = "password";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            while (rs.next())
            {

                System.out.println(String.format("%s %s", rs.getString("login"), rs.getTimestamp("create_date ")));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            //Log.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    //Log.error(e.getMessage(), e);
                    e.printStackTrace();
                }
            }
        }
        }
}

package control;


import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Tim on 20.04.2017.
 */
public class SQLConnection {
    Connection con;

    String host, username, password;

    public void connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con = (Connection) DriverManager.getConnection(host, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();


        }
    }

    public void select()
    {
        try {
            Statement st = con.createStatement();
            String sql = ("Select * from DPT");
            ResultSet res = st.executeQuery(sql);

            while ( res.next( ) ) {
                System.out.println(
                        res.getTimestamp("Timestamp") + " " +
                        res.getString("Name") + " " +
                        res.getTime("Time").toString() + " " +
                        res.getDate("Time").toString() + " " +
                        res.getString("Origin") + " "+
                        res.getString("Destination") + " " +
                        res.getInt("Status") + " "

                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

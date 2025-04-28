/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ashmi
 */
public class DBUtil {
    private static Connection conn;
    
    public static void openConnection(String dbUrl, String username, String passsword){
        if(conn==null){
            try{
                conn=DriverManager.getConnection(dbUrl, username, passsword);
                System.out.println("GadgetHub connection opened..!");
            }
            catch(SQLException se){
                System.out.println("error in openeing connection..!");
                se.printStackTrace();
            }
        }
    }
    public static void closeConnection(){
        if(conn!=null){
            try{
                conn.close();
            }
            catch(SQLException se){
                System.out.println("error in closing connection..!");
                se.printStackTrace();
            }
        }
    }
    public static Connection provideConnection(){
        return conn;
    }
    public static void closeResultSet(ResultSet rs){
        if(rs!=null){
            try{
                rs.close();
            }
            catch(SQLException se){
                System.out.println("error in closing resultSet..!");
                se.printStackTrace();
            }
        }
    }
    public static void closeStatement(Statement st){
        if(st!=null){
            try{
                st.close();
            }
            catch(SQLException se){
                System.out.println("error in closing Statement..!");
                se.printStackTrace();
            }
        }
    }
}

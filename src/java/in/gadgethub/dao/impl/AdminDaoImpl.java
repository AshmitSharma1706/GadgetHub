/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.AdminDao;
import in.gadgethub.pojo.AdminPojo;
import in.gadgethub.utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ashmi
 */
public class AdminDaoImpl implements AdminDao{

    @Override
    public boolean isRegistered(String emailId) {
        boolean result=false;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Connection conn=DBUtil.provideConnection();
            ps=conn.prepareStatement("select 1 from admins where adminemail=?");
            ps.setString(1, emailId);
            rs=ps.executeQuery();
            if(rs.next()){
                result=true;
            }
        } catch (SQLException ex) {
            System.out.println("Error in user existence..!  in method isRegister() of class AdminDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return result;
    }

    @Override
    public String isValidCredentials(String emailId, String password) {
        String validity="Login failed... Invalid username/password";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Connection conn=DBUtil.provideConnection();
            ps=conn.prepareStatement("select 1 from admins where adminemail=? and password=?");
            ps.setString(1, emailId);
            ps.setString(2, password);
            rs=ps.executeQuery();
            if(rs.next()){
                validity="Login successfull..!";
            }      
        } catch (SQLException ex) {
            System.out.println("Error in validating Credentials..!  in method isValidCredentials() of class AdminDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return validity;
    }

    @Override
    public AdminPojo getAdminDetails(String emailId) {
        AdminPojo adminDetail=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Connection conn=DBUtil.provideConnection();
            ps=conn.prepareStatement("select * from admins where adminemail=?");
            ps.setString(1, emailId);
            rs=ps.executeQuery();
            if(rs.next()){
                adminDetail=new AdminPojo();
                adminDetail.setAdminemail(rs.getString("adminemail"));
                adminDetail.setAdminname(rs.getString("adminname"));
                adminDetail.setMobile(rs.getString("mobile"));
                adminDetail.setAddress(rs.getString("address"));
                adminDetail.setPincode(rs.getInt("pincode"));
                adminDetail.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in admin data retreival..!  in method getAdminDetails() of class AdminDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return adminDetail;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.UserDao;
import in.gadgethub.pojo.UserPojo;
import in.gadgethub.utility.DBUtil;
import in.gadgethub.utility.Mailing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.MessagingException;

/**
 *
 * @author ashmi
 */
public class UserDaoImpl implements UserDao{

    @Override
    public String registerUser(UserPojo user){
        String status="Registration failed..!";
        if(isRegistered(user.getUseremail())){
            status="This email is already registered..!";
            return status;
        }
        PreparedStatement ps=null;
        try {
            Connection conn=DBUtil.provideConnection();
            ps=conn.prepareStatement("insert into users values(?,?,?,?,?,?)");
            ps.setString(1, user.getUseremail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getMobile());
            ps.setString(4, user.getAddress());
            ps.setInt(5, user.getPincode());
            ps.setString(6, user.getPassword());
            int result=ps.executeUpdate();
            if(result==1){
                status="Registration Successful. Please login first..!";
                Mailing.registrationSuccess(user.getUseremail(), user.getUsername());
                status="Registration Successful. Mail sent to you. Please login first..!";
            }
        } catch (SQLException ex) {
            System.out.println("Error in user registration..!  in method registerUser() of class UserDaoImpl..!");
            ex.printStackTrace();
        }catch(MessagingException me){
            System.out.println("Error in sneding mail..!  in method registerUser() of class UserDaoImpl..!");
            me.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public boolean isRegistered(String emailId) {
        boolean result=false;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Connection conn=DBUtil.provideConnection();
            ps=conn.prepareStatement("select 1 from users where useremail=?");
            ps.setString(1, emailId);
            rs=ps.executeQuery();
            if(rs.next()){
                result=true;
            }
        } catch (SQLException ex) {
            System.out.println("Error in user existence..!  in method isRegister() of class UserDaoImpl..!");
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
            ps=conn.prepareStatement("select 1 from users where useremail=? and password=?");
            ps.setString(1, emailId);
            ps.setString(2, password);
            rs=ps.executeQuery();
            if(rs.next()){
                validity="Login successfull..!";
            }      
        } catch (SQLException ex) {
            System.out.println("Error in validating Credentials..!  in method isValidCredentials() of class UserDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return validity;
    }

    @Override
    public UserPojo getUserDetails(String emailId) {
        UserPojo userDetail=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Connection conn=DBUtil.provideConnection();
            ps=conn.prepareStatement("select * from users where useremail=?");
            ps.setString(1, emailId);
            rs=ps.executeQuery();
            if(rs.next()){
                userDetail=new UserPojo();
                userDetail.setUseremail(rs.getString("useremail"));
                userDetail.setUsername(rs.getString("username"));
                userDetail.setMobile(rs.getString("mobile"));
                userDetail.setAddress(rs.getString("address"));
                userDetail.setPincode(rs.getInt("pincode"));
                userDetail.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in user data retreival..!  in method getUserDetails() of class UserDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return userDetail;
    }

    @Override
    public String getUserFirstName(String emailId) {
        String fName=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Connection conn=DBUtil.provideConnection();
            ps=conn.prepareStatement("select username from users where useremail=?");
            ps.setString(1, emailId);
            rs=ps.executeQuery();
            if(rs.next()){
                String fullName=rs.getString(1);
                fName=fullName.split(" ")[0];
            }     
        } catch (SQLException ex) {
            System.out.println("Error in user name retreival..!  in method getUserFirstName() of class UserDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return fName;
    }

    @Override
    public String getUserAddress(String emailId) {
        String address=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Connection conn=DBUtil.provideConnection();
            ps=conn.prepareStatement("select address from users where useremail=?");
            ps.setString(1, emailId);
            rs=ps.executeQuery();
            if(rs.next()){
                address=rs.getString(1);
            }   
        } catch (SQLException ex) {
            System.out.println("Error in user address retreival..!  in method getUserAddress() of class UserDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return address;
    }
}

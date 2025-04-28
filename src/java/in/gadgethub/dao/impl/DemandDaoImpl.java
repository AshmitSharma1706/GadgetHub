/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.DemandDao;
import in.gadgethub.pojo.DemandPojo;
import in.gadgethub.utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ashmi
 */
public class DemandDaoImpl implements DemandDao{

    @Override
    public boolean addProduct(DemandPojo demandPojo) {
        boolean status=false;
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps1=null;
        PreparedStatement ps2=null;
        try{
            ps1=conn.prepareStatement("Update userdemand set quantity=quantity+? where useremail=? and prodid=?");
            ps1.setInt(1, demandPojo.getQuantity());
            ps1.setString(2, demandPojo.getUseremail());
            ps1.setString(3, demandPojo.getProductId());
            int result=ps1.executeUpdate();
            if (result!=1) {
                ps2=conn.prepareStatement("insert into userdemand values(?,?,?)");
                ps2.setString(1, demandPojo.getUseremail());
                ps2.setString(2, demandPojo.getProductId());
                ps2.setInt(3, demandPojo.getQuantity());
                int done=ps2.executeUpdate();
                status=true;
            }            
        }
        catch (SQLException ex) {
            System.out.println("Error in demanding product..!  in method addProduct() of class DemandDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps1);
        DBUtil.closeStatement(ps2);
        return status;
    }

    @Override
    public boolean removeProduct(String userId, String prodId) {
        boolean status=false;
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("Delete from userdemand where useremail=? and prodid=?");
            ps.setString(1, userId);
            ps.setString(2, prodId);
            status=ps.executeUpdate()>0;
        }
        catch (SQLException ex) {
            System.out.println("Error in removing product..!  in method removeProduct() of class DemandDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public List<DemandPojo> haveDemanded(String prodId) {
        List<DemandPojo> productList=new ArrayList<>();
        DemandPojo demand=null;
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement("Select * from userdemand where prodid=?");
            ps.setString(1, prodId);
            rs=ps.executeQuery();
            while (rs.next()) {                
                demand=new DemandPojo();
                demand.setUseremail(rs.getString("useremail"));
                demand.setProductId(rs.getString("prodid"));
                demand.setQuantity(rs.getInt("quantity"));
                productList.add(demand);
            }
        }
        catch (SQLException ex) {
            System.out.println("Error in retrieving demanded..!  in method haveDemanded() of class DemandDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return productList;
    }
    
}

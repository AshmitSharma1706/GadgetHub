/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.TransactionDao;
import in.gadgethub.utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ashmi
 */
public class TransactionDaoImpl implements TransactionDao {

    @Override
    public String getUserId(String TransId) {
        String userId = "";
        Connection conn = DBUtil.provideConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select useremail from transactions where transid=?");
            ps.setString(1, TransId);
            rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getString(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error in retrieving user id..!  in method getUserId() of class TransactionDaoImpl..!" + ex);
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return userId;
    }
}


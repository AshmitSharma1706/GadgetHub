/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao;

import in.gadgethub.pojo.AdminPojo;

/**
 *
 * @author ashmi
 */
public interface AdminDao {
    boolean isRegistered(String emailId);

    String isValidCredentials(String emailId, String password);

    AdminPojo getAdminDetails(String emailId);
}

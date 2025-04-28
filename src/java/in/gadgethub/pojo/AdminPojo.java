/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.pojo;

/**
 *
 * @author ashmi
 */
public class AdminPojo {
    private String adminname;
    private String adminemail;
    private String mobile;
    private String address;
    private int pincode;
    private String password;

    public AdminPojo() {
    }

    public AdminPojo(String adminname, String adminemail, String mobile, String address, int pincode, String password) {
        this.adminname = adminname;
        this.adminemail = adminemail;
        this.mobile = mobile;
        this.address = address;
        this.pincode = pincode;
        this.password = password;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminemail() {
        return adminemail;
    }

    public void setAdminemail(String adminemail) {
        this.adminemail = adminemail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminPojo{" + "adminname=" + adminname + ", adminemail=" + adminemail + ", mobile=" + mobile + ", address=" + address + ", pincode=" + pincode + ", password=" + password + '}';
    }
    
}

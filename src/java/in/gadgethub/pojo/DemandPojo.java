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
public class DemandPojo {
    private String useremail;
    private String productId;
    private int demandQuantity;

    public DemandPojo() {
    }

    public DemandPojo(String useremail, String productId, int demandQuantity) {
        this.useremail = useremail;
        this.productId = productId;
        this.demandQuantity = demandQuantity;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return demandQuantity;
    }

    public void setQuantity(int demandQuantity) {
        this.demandQuantity = demandQuantity;
    }

    @Override
    public String toString() {
        return "CartPojo{" + "useremail=" + useremail + ", productId=" + productId + ", quantity=" + demandQuantity + '}';
    }
}

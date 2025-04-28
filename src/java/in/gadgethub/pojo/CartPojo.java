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
public class CartPojo {
    private String useremail;
    private String productId;
    private int quantity;

    public CartPojo() {
    }

    public CartPojo(String useremail, String productId, int quantity) {
        this.useremail = useremail;
        this.productId = productId;
        this.quantity = quantity;
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
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartPojo{" + "useremail=" + useremail + ", productId=" + productId + ", quantity=" + quantity + '}';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.pojo;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author ashmi
 */
public class OrderDetailsPojo {
    private String orderId;
    private String productId;
    private String productName;
    private int quantity;
    private double amount;
    private int shipped;
    private Date time;
    private InputStream productImage;

    public OrderDetailsPojo() {
    }

    public OrderDetailsPojo(String orderId, String productId, String productName, int quantity, double amount, int shipped, Date time, InputStream productImage) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.amount = amount;
        this.shipped = shipped;
        this.time = time;
        this.productImage = productImage;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getShipped() {
        return shipped;
    }

    public void setShipped(int shipped) {
        this.shipped = shipped;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public InputStream getProductImage() {
        return productImage;
    }

    public void setProductImage(InputStream productImage) {
        this.productImage = productImage;
    }    
}

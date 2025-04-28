/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.pojo;

import java.io.InputStream;
import java.io.Serializable;

/**
 *
 * @author ashmi
 */
public class ProductPojo implements Serializable {
    private String productId;
    private String productName;
    private String productType;
    private String productInfo;
    private double productPrice;
    private int productQuantity;
    private InputStream productImage;

    public ProductPojo() {
    }

    public ProductPojo(String productId, String productName, String productType, String productInfo, double productPrice, int productQuantity, InputStream productImage) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productInfo = productInfo;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productImage = productImage;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public InputStream getProductImage() {
        return productImage;
    }

    public void setProductImage(InputStream productImage) {
        this.productImage = productImage;
    }
}

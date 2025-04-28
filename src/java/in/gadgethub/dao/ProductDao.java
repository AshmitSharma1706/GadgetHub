/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao;

import in.gadgethub.pojo.ProductPojo;
import java.util.List;

/**
 *
 * @author ashmi
 */
public interface ProductDao {
    String addProduct(ProductPojo product);
    String updateProduct(ProductPojo prevProduct, ProductPojo updatedProduct);
    String updateProductPrice(String prodId, double price);
    List<ProductPojo> getAllProducts();
    List<ProductPojo> getAllProductsByType(String type);
    List<ProductPojo> searchAllProducts(String search);
    ProductPojo getProductDetails(String prodId);
    int getProductQuantity(String prodId);
    String updateProductWithoutImage(String prevProductId, ProductPojo updatedProduct);
    double getProductPrice(String prodId);
    boolean sellNProduct(String prodId, int n);
    List<String> getAllProductType();
    byte[] getImage(String prodId);
    String removeProduct(String prodId);
}

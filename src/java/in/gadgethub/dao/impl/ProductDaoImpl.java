/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.ProductDao;
import in.gadgethub.pojo.ProductPojo;
import in.gadgethub.utility.DBUtil;
import in.gadgethub.utility.IdUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ashmi
 */
public class ProductDaoImpl implements ProductDao{

    @Override
    public String addProduct(ProductPojo product) {
        String status="Product Registration Failed..!";
        if(product.getProductId()==null){
            product.setProductId(IdUtil.generateProductId());
        }
        PreparedStatement ps=null;
        try {
            Connection conn=DBUtil.provideConnection();
            ps=conn.prepareStatement("insert into products values(?,?,?,?,?,?,?,?)");
            ps.setString(1, product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getProductType());
            ps.setString(4, product.getProductInfo());
            ps.setDouble(5, product.getProductPrice());
            ps.setInt(6, product.getProductQuantity());
            ps.setBlob(7, product.getProductImage());
            ps.setString(8, "Y");
            int result=ps.executeUpdate();
            if(result==1){
                status="Product added Successfully..!  with ID:"+product.getProductId();
            }
        } catch (SQLException ex) {
            System.out.println("Error in product registration..!  in method addProduct() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public String updateProduct(ProductPojo prevProduct, ProductPojo updatedProduct) {
        String status="Product Updation Failed..!";
        if(!prevProduct.getProductId().equals(updatedProduct.getProductId())){
            status="Product ID'S do not match.  Updation failed..!";
        }
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement("update products set pname=?, ptype=?, pinfo=?, pprice=?, pquantity=?, image=? where pid=?");
            ps.setString(1, updatedProduct.getProductName());
            ps.setString(2, updatedProduct.getProductType());
            ps.setString(3, updatedProduct.getProductInfo());
            ps.setDouble(4, updatedProduct.getProductPrice());
            ps.setInt(5, updatedProduct.getProductQuantity());
            ps.setBlob(6, updatedProduct.getProductImage());
            ps.setString(7, updatedProduct.getProductId());
            int result=ps.executeUpdate();
            if(result==1){
                status="Product details updated Successfully..!";
            }
        } catch (SQLException ex) {
            System.out.println("Error in product updation..!  in method updateProduct() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public String updateProductPrice(String productId, double price) {
        String status="Product Price Updation Failed..!";
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement("update products set pprice=? where pid=? and available='Y'");
            ps.setDouble(1, price);
            ps.setString(2, productId);
            int result=ps.executeUpdate();
            if(result==1){
                status="Product details updated Successfully..!";
            }
        } catch (SQLException ex) {
            System.out.println("Error in product price updation..!  in method updateProductPrice() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public List<ProductPojo> getAllProducts() {
        List<ProductPojo> productLsit=new ArrayList<>();
        ProductPojo product;
        Connection conn=DBUtil.provideConnection();
        Statement st=null;
        ResultSet rs=null;
        try {
            st=conn.createStatement();
            rs=st.executeQuery("select * from products where available='Y'");
            while(rs.next()){
                product=new ProductPojo();
                product.setProductId(rs.getString("pid"));
                product.setProductName(rs.getString("pname"));
                product.setProductType(rs.getString("ptype"));
                product.setProductInfo(rs.getString("pinfo"));
                product.setProductPrice(rs.getDouble("pprice"));
                product.setProductQuantity(rs.getInt("pquantity"));
                product.setProductImage(rs.getAsciiStream("image"));
                productLsit.add(product);
            } 
        } catch (SQLException ex) {
            System.out.println("Error in retreiving all products..!  in method getAllProducts() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(st);
        return productLsit;
    }

    @Override
    public List<ProductPojo> getAllProductsByType(String type) {
        List<ProductPojo> productLsit=new ArrayList<>();
        ProductPojo product;
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        type=type.toLowerCase();
        try {
            ps=conn.prepareStatement("select * from products where lower(ptype) like ? and available='Y'");
            ps.setString(1, "%"+type+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                product=new ProductPojo();
                product.setProductId(rs.getString("pid"));
                product.setProductName(rs.getString("pname"));
                product.setProductType(rs.getString("ptype"));
                product.setProductInfo(rs.getString("pinfo"));
                product.setProductPrice(rs.getDouble("pprice"));
                product.setProductQuantity(rs.getInt("pquantity"));
                product.setProductImage(rs.getAsciiStream("image"));
                productLsit.add(product);
            } 
        } catch (SQLException ex) {
            System.out.println("Error in retreiving products by type..!  in method getAllProductsByType() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return productLsit;
    }

    @Override
    public List<ProductPojo> searchAllProducts(String search) {
        List<ProductPojo> productLsit=new ArrayList<>();
        ProductPojo product;
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        search=search.toLowerCase();
        try {
            ps=conn.prepareStatement("select * from products where lower(ptype) like ? or lower(pname) like ? or lower(pinfo) like ? and available='Y'");
            ps.setString(1, "%"+search+"%");
            ps.setString(2, "%"+search+"%");
            ps.setString(3, "%"+search+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                product=new ProductPojo();
                product.setProductId(rs.getString("pid"));
                product.setProductName(rs.getString("pname"));
                product.setProductType(rs.getString("ptype"));
                product.setProductInfo(rs.getString("pinfo"));
                product.setProductPrice(rs.getDouble("pprice"));
                product.setProductQuantity(rs.getInt("pquantity"));
                product.setProductImage(rs.getAsciiStream("image"));
                productLsit.add(product);
            } 
        } catch (SQLException ex) {
            System.out.println("Error in searching products..!  in method searchAllProducts() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return productLsit;
    }

    @Override
    public ProductPojo getProductDetails(String prodId) {
        ProductPojo product=null;
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement("select * from products where pid=? and available='Y'");
            ps.setString(1, prodId);
            rs=ps.executeQuery();
            if(rs.next()){
                product=new ProductPojo();
                product.setProductId(rs.getString("pid"));
                product.setProductName(rs.getString("pname"));
                product.setProductType(rs.getString("ptype"));
                product.setProductInfo(rs.getString("pinfo"));
                product.setProductPrice(rs.getDouble("pprice"));
                product.setProductQuantity(rs.getInt("pquantity"));
                product.setProductImage(rs.getAsciiStream("image"));
            } 
        } catch (SQLException ex) {
            System.out.println("Error in retreiving product details..!  in method getProductDetails() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return product;
    }

    @Override
    public int getProductQuantity(String prodId) {
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        int quantity=-1;
        try {
            ps=conn.prepareStatement("select pquantity from products where pid=? and available='Y'");
            ps.setString(1, prodId);
            rs=ps.executeQuery();
            if(rs.next()){
                quantity=rs.getInt(1);
            } 
        } catch (SQLException ex) {
            System.out.println("Error in retreiving product quantity..!  in method getProductQuantity() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return quantity;
    }

    @Override
    public String updateProductWithoutImage(String prevProductId, ProductPojo updatedProduct) {
        String status="Product Updation Failed..!";
        if(!prevProductId.equals(updatedProduct.getProductId())){
            status="Product ID'S do not match.  Updation failed..!";
        }
        int oldQty=getProductQuantity(prevProductId);
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement("update products set pname=?, ptype=?, pinfo=?, pprice=?, pquantity=? where pid=?");
            ps.setString(1, updatedProduct.getProductName());
            ps.setString(2, updatedProduct.getProductType());
            ps.setString(3, updatedProduct.getProductInfo());
            ps.setDouble(4, updatedProduct.getProductPrice());
            ps.setInt(5, updatedProduct.getProductQuantity());
            ps.setString(6, updatedProduct.getProductId());
            int result=ps.executeUpdate();
            if(result==1 && oldQty<updatedProduct.getProductQuantity()){
                status="Product details updated and mail sent Successfully..!";
                //code for email
            }
            else if(result==1){
                status="Product details updated Successfully..!";
            }
            else{
                status="Product updation failed, product does not exist of given ID..!";
            }
        } catch (SQLException ex) {
            System.out.println("Error in product updation without image..!  in method updateProductWithoutImage() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public double getProductPrice(String prodId) {
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        int price=-1;
        try {
            ps=conn.prepareStatement("select pprice from products where pid=? and available='Y'");
            ps.setString(1, prodId);
            rs=ps.executeQuery();
            if(rs.next()){
                price=rs.getInt(1);
            } 
        } catch (SQLException ex) {
            System.out.println("Error in retreiving product price..!  in method getProductPrice() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return price;
    }

    @Override
    public boolean sellNProduct(String prodId, int n) {
        boolean status=false;
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement("update products set pquantity=(pquantity-?) where pid=?");
            ps.setInt(1, n);
            ps.setString(2, prodId);
            int result=ps.executeUpdate();
            if(result==1){
                status=true;
            }
        } catch (SQLException ex) {
            System.out.println("Error in updating product price..!  in method sellNProduct() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public List<String> getAllProductType() {
        List<String> typeList=new ArrayList<>();
        Connection conn=DBUtil.provideConnection();
        Statement st=null;
        ResultSet rs=null;
        try {
            st=conn.createStatement();
            rs=st.executeQuery("select distinct ptype from products where available='Y'");
            while(rs.next()){
                typeList.add(rs.getString(1));
            } 
        } catch (SQLException ex) {
            System.out.println("Error in retreiving product type..!  in method getAllProductType() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(st);
        return typeList;
    }

    @Override
    public byte[] getImage(String prodId) {
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        byte[] arr=null;
        try {
            ps=conn.prepareStatement("select image from products where pid=?");
            ps.setString(1, prodId);
            rs=ps.executeQuery();
            if(rs.next()){
                arr=rs.getBytes(1);
            } 
        } catch (SQLException ex) {
            System.out.println("Error in retreiving product image..!  in method getImage() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return arr;
    }

    @Override
    public String removeProduct(String prodId) {
        String status="Product not found..!";
        Connection conn=DBUtil.provideConnection();
        PreparedStatement ps1=null;
        PreparedStatement ps2=null;
        try{
            ps1=conn.prepareStatement("Update products set available='N' where pid=? and available='Y'");
            ps1.setString(1, prodId);
            int result=ps1.executeUpdate();
            if(result>0){
                status="Product removed successfully..!";
                ps2=conn.prepareStatement("Delete from usercart where prodid=?");
                ps2.setString(1, prodId);
                result=ps2.executeUpdate();
            }
            
        }
        catch (SQLException ex) {
            System.out.println("Error in product removal..!  in method removeProduct() of class ProductDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps1);
        DBUtil.closeStatement(ps2);
        return status;
    }
    
}

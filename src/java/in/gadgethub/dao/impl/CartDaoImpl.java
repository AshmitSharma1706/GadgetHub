/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.CartDao;
import in.gadgethub.pojo.CartPojo;
import in.gadgethub.pojo.DemandPojo;
import in.gadgethub.utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ashmi
 */
public class CartDaoImpl implements CartDao{

    @Override
    public String addProductToCart(CartPojo cart) {
        Connection conn=DBUtil.provideConnection();
        String status="Failed..! Product not added to cart..!";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement("select * from usercart where useremail=? and prodid=?");
            ps.setString(1, cart.getUseremail());
            ps.setString(2, cart.getProductId());
            rs=ps.executeQuery();
            if(rs.next()){
                ProductDaoImpl product=new ProductDaoImpl();
                int stockQty=product.getProductQuantity(cart.getProductId());
                int newtQty=rs.getInt("quantity") + cart.getQuantity();
                if(stockQty<newtQty){
                    cart.setQuantity(stockQty);
                    updateProductInCart(cart);
                    status="Only "+stockQty+" no. of items are available in our stock so we are adding "+stockQty+" in your cart";
                    DemandPojo demand=new DemandPojo();
                    demand.setProductId(cart.getProductId());
                    demand.setUseremail(cart.getUseremail());
                    demand.setQuantity(newtQty-stockQty);
                    DemandDaoImpl demandImpl=new DemandDaoImpl();
                    boolean result=demandImpl.addProduct(demand);
                    if(result){
                        status+="We will mail you when "+(newtQty-stockQty)+" no. of items will be available";
                    }
                }
                else{
                    cart.setQuantity(newtQty);
                    status=updateProductInCart(cart);
                }
            }
        } 
        catch (Exception ex) {
            System.out.println("Error in product addition to cart..!  in method addProductToCart() of class CartDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public String updateProductInCart(CartPojo cart) {
        Connection conn=DBUtil.provideConnection();
        String status="Failed..! Product not added to cart..!";
        PreparedStatement ps1=null;
        PreparedStatement ps2=null;
        ResultSet rs=null;
        try {
            ps1=conn.prepareStatement("select * from usercart where useremail=? and prodid=?");
            ps1.setString(1, cart.getUseremail());
            ps1.setString(2, cart.getProductId());
            rs=ps1.executeQuery();
            if(rs.next()){
                int quantity=cart.getQuantity();
                if(quantity>0){
                    ps2=conn.prepareStatement("update usercart set quantity=? where useremail=? and prodid=?");
                    ps2.setInt(1, cart.getQuantity());
                    ps2.setString(2, cart.getUseremail());
                    ps2.setString(3, cart.getProductId());
                    int answer=ps2.executeUpdate();
                    if(answer>0){
                        status="Product updated to cart successfully..!";
                    }
                    else{
                        status="could not update the product..!";
                    }
                }
                else if(quantity==0){
                    ps2=conn.prepareStatement("delete from usercart where useremail=? and prodid=?");
                    ps2.setString(1, cart.getUseremail());
                    ps2.setString(2, cart.getProductId());
                    int answer=ps2.executeUpdate();
                    if(answer>0){
                        status="Product updated to cart successfully..!";
                    }
                    else{
                        status="could not update the product..!";
                    }
                }
            }
            else{
                ps2=conn.prepareStatement("Insert into usercart values(?,?,?)");
                ps2.setString(1, cart.getUseremail());
                ps2.setString(2, cart.getProductId());
                ps2.setInt(3, cart.getQuantity());
                int answer=ps2.executeUpdate();
                if(answer>0){
                    status="Product added to cart successfully..!";
                }
                else{
                    status="could not add the product to cart..!";
                }
            }
        } 
        catch (Exception ex) {
            System.out.println("Error in product updation to cart..!  in method updateProductInCart() of class CartDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps1);
        DBUtil.closeStatement(ps2);
        return status;
    }

    @Override
    public List<CartPojo> getAllCartItems(String userId) {
        Connection conn=DBUtil.provideConnection();
        List<CartPojo> cartList=new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement("select * from usercart where useremail=?");
            ps.setString(1, userId);
            rs=ps.executeQuery();
            while (rs.next()) {                
                CartPojo cart=new CartPojo();
                cart.setProductId(rs.getString("prodid"));
                cart.setUseremail(rs.getString("useremail"));
                cart.setQuantity(rs.getInt("quantity"));
                cartList.add(cart);
            }
        }
        catch (Exception ex) {
            System.out.println("Error in product retrieval to cart..!  in method getAllCartItems() of class CartDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return cartList;
    }

    @Override
    public int getCartItemCount(String userId, String prodId) {
        if(userId==null || prodId==null){
            return 0;
        }
        Connection conn=DBUtil.provideConnection();
        int count=0;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            ps=conn.prepareStatement("select quantity from usercart where useremail=? and prodid=?");
            ps.setString(1, userId);
            ps.setString(2, prodId);
            rs=ps.executeQuery();
            if(rs.next()){
                count=rs.getInt(1);
            }
        }
        catch (Exception ex) {
            System.out.println("Error in product count from cart..!  in method getCartItemCount() of class CartDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return count;
    }

    @Override
    public String removeProductFromCart(String userId, String prodId) {
        Connection conn=DBUtil.provideConnection();
        String status="Failed..! Product not updated to cart..!";
        PreparedStatement ps1=null;
        PreparedStatement ps2=null;
        ResultSet rs=null;
        try {
            ps1=conn.prepareStatement("select * from usercart where useremail=? and prodid=?");
            ps1.setString(1, userId);
            ps1.setString(2, prodId);
            rs=ps1.executeQuery();
            if(rs.next()){
                int qty=rs.getInt("quantity");
                qty=-1;
                if(qty>0){
                    ps2=conn.prepareStatement("Update usercart set quantity=? where useremail=? and prodid=?");
                    ps2.setInt(1, qty);
                    ps2.setString(2, userId);
                    ps2.setString(3, prodId);
                    int result=ps2.executeUpdate();
                    if(result>0){
                        status="Product removed from cart successfully..!";
                    }
                }
                else{
                    ps2=conn.prepareStatement("delete from usercart where useremail=? and prodid=?");
                    ps2.setString(1, userId);
                    ps2.setString(2, prodId);
                    int result=ps2.executeUpdate();
                    if(result>0){
                        status="Product removed from cart successfully..!";
                    }
                }
            }    
        }
        catch (Exception ex) {
            System.out.println("Error in product removal from cart..!  in method getAllCartItems() of class CartDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps1);
        DBUtil.closeStatement(ps2);
        return status;
    }

    @Override
    public boolean removeAProduct(String userId, String prodId) {
        Connection conn=DBUtil.provideConnection();
        boolean status=false;
        PreparedStatement ps=null;
        try{
            ps=conn.prepareStatement("delete from usercart where useremail=? and prodid=?");
            ps.setString(1, userId);
            ps.setString(2, prodId);
            int result=ps.executeUpdate();
            if(result>0){
                status=true;
            }
        }
        catch (Exception ex) {
            System.out.println("Error in product removal from cart..!  in method removeAProduct() of class CartDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }
    
}

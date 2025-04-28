/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.dao.impl;

import in.gadgethub.dao.OrderDao;
import in.gadgethub.dao.TransactionDao;
import in.gadgethub.dao.UserDao;
import in.gadgethub.pojo.CartPojo;
import in.gadgethub.pojo.OrderDetailsPojo;
import in.gadgethub.pojo.OrderPojo;
import in.gadgethub.pojo.TransactionPojo;
import in.gadgethub.utility.DBUtil;
import in.gadgethub.utility.IdUtil;
import in.gadgethub.utility.Mailing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;

/**
 *
 * @author ashmi
 */
public class OrderDaoImpl implements OrderDao {

    @Override
    public boolean addOrder(OrderPojo order) {
        boolean status = false;
        Connection conn = DBUtil.provideConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("Insert into orders values(?,?,?,?,?)");
            ps.setString(1, order.getOrderId());
            ps.setString(2, order.getProductId());
            ps.setInt(3, order.getQuantity());
            ps.setDouble(4, order.getAmount());
            ps.setInt(5, 0);
            int count = ps.executeUpdate();
            status = count > 0;
        } catch (Exception ex) {
            System.out.println("Error in adding order..!  in method addOrder() of class OrderDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public boolean addTransaction(TransactionPojo transaction) {
        boolean status = false;
        Connection conn = DBUtil.provideConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("Insert into transactions values(?,?,?,?)");
            ps.setString(1, transaction.getTransactionId());
            ps.setString(2, transaction.getUseremail());
            java.util.Date d1 = transaction.getTransactionDate();
            java.sql.Date d2 = new java.sql.Date(d1.getTime());
            ps.setDate(3, d2);
            ps.setDouble(4, transaction.getAmount());
            int count = ps.executeUpdate();
            status = count > 0;
        } catch (Exception ex) {
            System.out.println("Error in adding transaction..!  in method addTransaction() of class OrderDaoImpl..!");
            ex.printStackTrace();
        }
        return status;
    }

    @Override
    public List<OrderPojo> getAllOrders() {
        List<OrderPojo> orderList = new ArrayList<>();
        Connection conn = DBUtil.provideConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from Orders");
            while (rs.next()) {
                OrderPojo order = new OrderPojo();
                order.setOrderId(rs.getString("orderid"));
                order.setProductId(rs.getString("prodid"));
                order.setQuantity(rs.getInt("quantity"));
                order.setShipped(rs.getInt("shipped"));
                order.setAmount(rs.getDouble("amount"));
                orderList.add(order);
            }
        } catch (SQLException ex) {
            System.out.println("Error in retrieving orders..!  in method getAllOders() of class OrderDaoImpl..!");
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(st);
        return orderList;
    }

    @Override
    public List<OrderDetailsPojo> getAllOrderDetails(String userEmail) {
        List<OrderDetailsPojo> orderList = new ArrayList<>();
        Connection conn = DBUtil.provideConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select p.pid as prodid, o.orderid as orderid, o.shipped as shipped, p.image as image, p.pname as name, o.quantity as qty, o.amount as amount, t.transtime as time from orders o, products p, transactions t where o.orderid=t.transid and o.prodid=p.pid and t.useremail=?");
            ps.setString(1, userEmail);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetailsPojo orderDetails = new OrderDetailsPojo();
                orderDetails.setOrderId(rs.getString("orderid"));
                orderDetails.setProductImage(rs.getAsciiStream("image"));
                orderDetails.setProductId(rs.getString("prodid"));
                orderDetails.setProductName(rs.getString("name"));
                orderDetails.setQuantity(rs.getInt("qty"));
                orderDetails.setAmount(rs.getDouble("amount"));
                orderDetails.setTime(rs.getTimestamp("time"));
                orderDetails.setShipped(rs.getInt("shipped"));
                orderList.add(orderDetails);
            }
        } catch (SQLException ex) {
            System.out.println("Error in retrieving order..!  in method getAllOrderDetails() of class OrderDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return orderList;
    }

    @Override
    public String shipNow(String orderId, String productId) {
        String status = "Failed! Cannot ship the item..!";
        Connection conn = DBUtil.provideConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("Update orders set shipped=1 where orderid=? and prodid=?");
            ps.setString(1, orderId);
            ps.setString(2, productId);
            int count = ps.executeUpdate();
            if (count > 0) {
                status = "Order has been shipped successfully";
                TransactionDao transactionDao=new TransactionDaoImpl();
                UserDao userDao=new UserDaoImpl();
                Mailing.orderShipped(transactionDao.getUserId(orderId), userDao.getUserFirstName(transactionDao.getUserId(orderId)), orderId, productId);
                status = "Order has been shipped successfully. Mail has been sent to you..!";
            }
        } catch (SQLException ex) {
            System.out.println("Error in shipping order..!  in method shipNow() of class OrderDaoImpl..!");
            ex.printStackTrace();
        } catch (MessagingException me) {
            System.out.println("Error in sending mail..!  in method shipNow() of class OrderDaoImpl..!");
            me.printStackTrace();
        }
        DBUtil.closeStatement(ps);
        return status;
    }

    @Override
    public String paymentSuccess(String username, double paidAmount) {
        String status = "order placement failed";
        try {
            CartDaoImpl cartDao = new CartDaoImpl();
            List<CartPojo> cartList = cartDao.getAllCartItems(username);
            if (cartList.isEmpty()) {
                return status;
            }
            String transactionId = IdUtil.generateOrderId();
            TransactionPojo transPojo = new TransactionPojo();
            transPojo.setTransactionId(transactionId);
            transPojo.setUseremail(username);
            transPojo.setAmount(paidAmount);
            transPojo.setTransactionDate(new java.util.Date());
            boolean result = addTransaction(transPojo);
            if (!result) {
                return status;
            }
            boolean ordered = true;
            ProductDaoImpl product = new ProductDaoImpl();
            OrderPojo order = new OrderPojo();
            for (CartPojo cart : cartList) {
                double amount = product.getProductPrice(cart.getProductId()) * cart.getQuantity();
                order.setOrderId(transactionId);
                order.setProductId(cart.getProductId());
                order.setQuantity(cart.getQuantity());
                order.setAmount(amount);
                order.setShipped(0);
                ordered = addOrder(order);
                if (!ordered) {
                    break;
                }
                ordered = cartDao.removeAProduct(cart.getUseremail(), cart.getProductId());
                if (!ordered) {
                    break;
                }
                ordered = product.sellNProduct(cart.getProductId(), cart.getQuantity());
                if (!ordered) {
                    break;
                }
            }
            if (ordered) {
                status = "Order placed successfully..!";
                UserDao userDao = new UserDaoImpl();
                Mailing.orderPlaced(username, userDao.getUserFirstName(username), order);
                status = "Order placed successfully. Mail has been sent to you..!";
            }
        }catch(MessagingException me){
            System.out.println("Error in sending mail..!  in method paymentSuccess() of class OrderDaoImpl..!");
            me.printStackTrace();
        }
        return status;
    }

    @Override
    public int getSoldQty(String productId) {
        Connection conn = DBUtil.provideConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            ps = conn.prepareStatement("select sum(quantity) as Qty from orders where prodid=?");
            ps.setString(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error in retrieving Qty..!  in method getSoldQty() of class OrderDaoImpl..!");
            ex.printStackTrace();
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(ps);
        return count;
    }

    @Override
    public List<OrderPojo> getAllShippedOrders() {
        List<OrderPojo> orderList = new ArrayList<>();
        Connection conn = DBUtil.provideConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from Orders where shipped=1");
            while (rs.next()) {
                OrderPojo order = new OrderPojo();
                order.setOrderId(rs.getString("orderid"));
                order.setProductId(rs.getString("prodid"));
                order.setQuantity(rs.getInt("quantity"));
                order.setShipped(rs.getInt("shipped"));
                order.setAmount(rs.getDouble("amount"));
                orderList.add(order);
            }
        } catch (SQLException ex) {
            System.out.println("Error in retrieving orders..!  in method getAllOders() of class OrderDaoImpl..!");
        }
        DBUtil.closeResultSet(rs);
        DBUtil.closeStatement(st);
        return orderList;
    }

}

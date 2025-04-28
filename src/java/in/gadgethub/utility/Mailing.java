/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.utility;

import in.gadgethub.pojo.OrderPojo;
import javax.mail.MessagingException;

/**
 *
 * @author ashmi
 */
public class Mailing {
    public static void registrationSuccess(String recipientMailId, String name) throws MessagingException {
        String subject = "Registration Successfull..!";
        String htmlTextMessage = "" + "<html>" + "<body>"
                + "<h2 style='color:green;'>Welcome to " + AppInfo.APP_NAME + "</h2>" + "" + "Hi " + name + ","
                + "<br><br>Thanks for singing up with " + AppInfo.APP_NAME + ".<br>"
                + "We are glad that you choose us. We invite you to check out our latest collection of new electonics appliances."
                + "<br>We are providing upto 60% OFF on most of the electronic gadgets. So please visit our site and explore the collections."
                + "<br><br>Our Online electronics is growing in a larger amount these days and we are in high demand so we thanks all of you for "
                + "making us up to that level. We Deliver Product to your house with no extra delivery charges and we also have collection of most of the"
                + "branded items.<br><br>As a Welcome gift for our New Customers we are providing additional 10% OFF Upto 500 Rs for the first product purchase. "
                + "<br>To avail this offer you only have "
                + "to enter the promo code given below.<br><br><br> PROMO CODE: " + "" + AppInfo.APP_NAME.toUpperCase() + "500<br><br><br>"
                + "Have a good day!<br>" + "" + "</body>" + "</html>";
        JavaMailUtil.sendMail(recipientMailId, subject, htmlTextMessage);
    }
    
    public static void orderPlaced(String recipientMailId, String name, OrderPojo order) throws MessagingException {
        String Subject = "Order Confirmation Mail..!";
        String shipped="Not Shipped";
        if(order.getShipped()==1){
            shipped="Shipped";
        }
        String htmlTextMessage = "" + "<html>" + "<body>"
                + "<h2 style='color:green;'>Welcome to " + AppInfo.APP_NAME + "</h2>" + "" + "Dear " + name + ","
                + "<br><br>Thanks for shoppint with us.<br>"
                + "<br>Your order has been placed."
                + "<br>Order Id: "+order.getOrderId()
                + "<br>Price: "+order.getAmount()
                + "<br>Quantity: "+order.getQuantity()
                + "<br>Shipping Status: "+shipped+"<br>"
                + "<br>Have a good day!<br>" + "" + "</body>" + "</html>";
        JavaMailUtil.sendMail(recipientMailId, Subject, htmlTextMessage);
    }
    
    public static void orderShipped(String recipientMailId, String name, String orderId, String productId) throws MessagingException {
        String Subject = "Order Shipped..!";
        String htmlTextMessage = "" + "<html>" + "<body>"
                + "<h2 style='color:green;'>Welcome to " + AppInfo.APP_NAME + "</h2>" + "" + "Dear " + name + ","
                + "<br><br>Your order has been Shipped."
                + "<br>Order Id: "+orderId
                + "<br>Product Id: "+productId+"<br>"
                + "<br>Have a good day!<br>" + "" + "</body>" + "</html>";
        JavaMailUtil.sendMail(recipientMailId, Subject, htmlTextMessage);
    }
}

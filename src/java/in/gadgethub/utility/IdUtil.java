/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ashmi
 */
public class IdUtil {
    public static String generateProductId(){
        Date today=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        String productId=sdf.format(today);
        productId="P"+productId;
        return productId;
    }
    public static String generateOrderId(){
        Date today=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        String orderId=sdf.format(today);
        orderId="T"+orderId;
        return orderId;
    }
}

<%-- 
    Document   : cartDetails
    Created on : 27 Dec, 2024, 8:56:28 PM
    Author     : ashmi
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
         pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*, in.gadgethub.utility.*, in.gadgethub.pojo.*, in.gadgethub.dao.*, in.gadgethub.dao.impl.*,javax.servlet.*,javax.servlet.ServletOutputStream.*,java.io.*"%>

<!DOCTYPE html> 
<html> 
    <head> 
        <title>Cart Details</title> 
        <meta charset="utf-8"> 
        <meta name="viewport" content="width=device-width, initial-scale=1"> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> 

        <link rel="stylesheet" href="mycss.css"> 
    </head> 
    <body style="background-color: #E6F9E6;"> 


        <jsp:include page="header.jsp" /> 

        <div class="text-center h3 m-3 text-primary">Cart Items</div> 
        <div class="container"> 

            <table class="table table-hover"> 
                <thead> 
                    <tr> 
                        <th>Picture</th> 
                        <th>Products</th> 
                        <th>Price</th> 
                        <th>Quantity</th> 
                        <th>Add</th> 
                        <th>Remove</th> 
                        <th>Amount</th> 
                    </tr> 
                </thead> 
                <tbody> 
                    <%
                        String userName=(String)request.getAttribute("username");
                        List<CartPojo> cartItems=(List<CartPojo>)request.getAttribute("cartItems");
                        Map<String,Object> map=(Map<String,Object>)request.getAttribute("map");
                        double totalAmount=0.0;
                        for(CartPojo item:cartItems){
                            String productId=item.getProductId();
                            int productQty=item.getQuantity();
                            ProductPojo product=(ProductPojo)map.get(productId);
                            double productPrice=product.getProductPrice();
                            double currAmount=productQty*productPrice;
                            totalAmount+=currAmount;
                            if(productQty>0){
                    %> 

                    <tr> 
                        <td><img src="./ShowImageServlet?pid=<%=product.getProductId()%>" 
                                 style="width: 50px; height: 50px;"></td> 
                        <td><%=product.getProductName()%></td> 
                        <td><%=product.getProductPrice()%></td> 
                        <td><form method="post" action="./UpdateToCartServlet"> 
                                <input type="number" name="pqty" value="<%=productQty%>" 
                                       style="max-width: 70px;" min="0"><input type="hidden" 
                                       name="pid" value="<%=product.getProductId()%>"><input 
                                       type="submit" name="Update" value="Update" 
                                       style="max-width: 80px;"> 
                            </form></td> 
                        <td><a 
                                href="./CartDetailServlet?add=1&uid=<%=userName%>&pid=<%=product.getProductId()%>&avail=<%=product.getProductQuantity()%>&qty=<%=productQty%>"><i 
                                    class="fa fa-plus"></i></a></td> 
                        <td><a 
                                href="./CartDetailServlet?add=0&uid=<%=userName%>&pid=<%=product.getProductId()%>&avail=<%=product.getProductQuantity()%>&qty=<%=productQty%>"><i 
                                    class="fa fa-minus"></i></a></td> 
                        <td><%=currAmount%></td> 
                    </tr> 

                    <%
                        }
                    }
                    if(cartItems.size()==0){
                    %> 
                    <tr style="background-color: grey; color: white;">
                            <td colspan="7" style="text-align: center;">No Items
                                Available</td>
                    </tr>
                    <%
                        }
                    %>
                    <tr style="background-color: grey; color: white;"> 
                        <td colspan="6" style="text-align: center;">Total Amount to 
                            Pay (in Rupees)</td> 
                        <td><%=totalAmount%></td> 
                    </tr> 
                    <% 
                        if(totalAmount!=0){ 
                    %> 
                    <tr style="background-color: grey; color: white;"> 
                        <td colspan="4" style="text-align: center;"> 
                        <td><form method="post"> 
                                <button formaction="UserHomeServlet" 
                                        style="background-color: black; color: white;">Cancel</button> 
                            </form></td> 
                        <td colspan="2" align="center"><form method="post"> 
                                <button style="background-color: blue; color: white;" 
                                        formaction="payment.jsp?amount=<%=totalAmount%>">Pay Now</button> 
                            </form></td> 

                    </tr> 
                    <% 
                    } 
                    %> 
                </tbody> 
            </table> 
        </div> 
        <!-- ENd of Product Items List --> 


        <%@ include file="footer.jsp"%> 

    </body> 
</html>

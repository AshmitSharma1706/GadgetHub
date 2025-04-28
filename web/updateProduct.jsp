<%-- 
    Document   : updateProduct
    Created on : 17 Dec, 2024, 8:56:27 PM
    Author     : ashmi
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
         pageEncoding="ISO-8859-1"%> 
<%@page import="java.util.*, in.gadgethub.pojo.*, in.gadgethub.dao.*, in.gadgethub.dao.impl.*,javax.servlet.*,java.io.*"%>

<!DOCTYPE html> 
<html> 
    <head> 
        <title>Update Product</title> 
        <meta charset="utf-8"> 
        <meta name="viewport" content="width=device-width, initial-scale=1"> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> 
        <link rel="stylesheet" href="mycss.css"> 
    </head> 
    <body style="background-color: #E6F9E6;"> 

        <jsp:include page="header.jsp" /> 

        <%
            String message =request.getParameter("message");
            ProductPojo product=(ProductPojo)request.getAttribute("product");
        %> 
        <div class="container"> 
            <div class="row justify-content-center"> 
                <form action="./UpdateProductServlet" method="post" class="col-md-6 col-md-offset-3 myform"> 
                    <div class="text-center"> 
                        <div class="form-group"> 
                            <img src="./ShowImageServlet?pid=<%=product.getProductId()%>" alt="Product Image" height="100px" /> 
                            <h2 class="text-primary">Product Update Form</h2> 
                        </div> 

                        <% 
                            if (message != null) {
                        %> 
                        <p style="color: blue;"> 
                            <%=message%> 
                        </p> 
                        <%
                            }
                        %> 
                    </div> 

                    <div class="row"> 
                        <input type="hidden" name="pid" class="form-control" value="<%=product.getProductId()%>" id="last_name" required> 
                    </div> 

                    <div class="row"> 
                        <div class="col-md-6 form-group"> 
                            <label for="last_name">Product Name</label> 
                            <input type="text" placeholder="Enter Product Name" name="name" class="form-control" value="<%=product.getProductName()%>" id="last_name" required> 
                        </div> 
                        <div class="col-md-6 form-group"> 
                            <%
                                String pType=product.getProductType();
                            %> 
                            <label for="productType">Product Type</label> 
                            <select name="type" id="productType" class="form-control" required> 
                                <option value="mobile" <%=pType.equalsIgnoreCase("mobile")?"selected":""%>>MOBILE</option> 
                                <option value="tv" <%=pType.equalsIgnoreCase("TV")?"selected":""%>>TV</option> 
                                <option value="camera" <%=pType.equalsIgnoreCase("camera")?"selected":""%>>CAMERA</option> 
                                <option value="laptop" <%=pType.equalsIgnoreCase("laptop")?"selected":""%>>LAPTOP</option> 
                                <option value="tablet" <%=pType.equalsIgnoreCase("tablet")?"selected":""%>>TABLET</option> 
                                <option value="speaker" <%=pType.equalsIgnoreCase("speaker")?"selected":""%>>SPEAKER</option> 
                                <option value="other" <%=pType.equalsIgnoreCase("other")?"selected":""%>>Some Other Appliances</option> 
                            </select> 
                        </div> 
                    </div> 

                    <div class="form-group"> 
                        <label for="last_name">Product Description</label> 
                        <textarea name="info" class="form-control text-align-left" id="last_name" required><%=product.getProductInfo()%></textarea> 
                    </div> 

                    <div class="row"> 
                        <div class="col-md-6 form-group"> 
                            <label for="last_name">Unit Price</label> 
                            <input type="number" value="<%=product.getProductPrice()%>" placeholder="Enter Unit Price" name="price" class="form-control" id="last_name" required> 
                        </div> 
                        <div class="col-md-6 form-group"> 
                            <label for="last_name">Stock Quantity</label> 
                            <input type="number" value="<%=product.getProductQuantity()%>" placeholder="Enter Stock Quantity" class="form-control" id="last_name" name="quantity" required> 
                        </div> 
                    </div> 
                    <br/> 

                    <div class="row text-center"> 
                        <!-- Separate Form for Cancel Button --> 
                        <div class="col-md-6"> 
                            <form action="./AdminViewServlet" method="get"> 
                                <button type="submit" formaction="./AdminViewServlet" class="btn btn-danger">Cancel</button> 
                            </form> 
                        </div> 

                        <!-- Submit Button for Update Product --> 
                        <div class="col-md-6"> 
                            <button type="submit" class="btn btn-success">Update Product</button> 
                        </div> 
                    </div> 
                </form> 
            </div> 
        </div> 

        <%@ include file="footer.jsp"%> 
    </body> 
</html>

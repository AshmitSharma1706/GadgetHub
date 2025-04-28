<%-- 
    Document   : userName
    Created on : 20 Dec, 2024, 9:41:59 PM
    Author     : ashmi
--%>

<%@page import="in.gadgethub.utility.AppInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*, in.gadgethub.utility.*, in.gadgethub.pojo.*, in.gadgethub.dao.*, in.gadgethub.dao.impl.*,javax.servlet.*,javax.servlet.ServletOutputStream.*,java.io.*"%>

<!DOCTYPE html>
<html>
    <head>
        <title><%=AppInfo.APP_NAME%> Application</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

        <link rel="stylesheet" href="mycss.css">
    </head>
    <body style="background-color: #E6F9E6;">

        <jsp:include page="header.jsp" />
        <%
            if (request.getAttribute("message") != null) {
        %>
        <div class="text-center h3 text-primary m-3" id="message"><%=request.getAttribute("message")%></div>
        <%
            }
        %>
        <!-- <script>document.getElementById('mycart').innerHTML='<i data-count="20" class="fa fa-shopping-cart fa-3x icon-white badge" style="background-color:#333;margin:0px;padding:0px; margin-top:5px;"></i>'</script>
        -->
        <!-- Start of Product Items List -->
        <div class="container">
            <div class="row text-center">

                <%
                    Map<String, Integer> mapList = (HashMap) session.getAttribute("map");
                    String username = (String) session.getAttribute("username");
                    List<ProductPojo> prodList = (ArrayList<ProductPojo>) session.getAttribute("products");
                    for (ProductPojo product : prodList) {
                        int cartQty = mapList.get(product.getProductId());
                %>
                <div class="col-sm-4">
                    <div class="thumbnail mt-3 mb-3">
                        <img src="./ShowImageServlet?pid=<%=product.getProductId()%>" alt="Product"
                             style="height: 150px; max-width: 180px" class="mt-3">
                        <p class="productname"><%=product.getProductName()%>
                        </p>
                        <%
                            String desc = product.getProductInfo();
                            desc = desc.substring(0, Math.min(100, desc.length()));
                        %>
                        <p class="productinfo"><%=desc%>..
                        </p>
                        <p class="price">
                            Rs <%=product.getProductPrice()%>
                        </p>
                        <form method="post">
                            <%
                                if (cartQty == 0) {
                            %>
                            <button type="submit"
                                    formaction="./AddToCartServlet?uid=<%=username%>&pid=<%=product.getProductId()%>&pqty=1"
                                    class="btn btn-warning">Add to Cart</button>
                            &nbsp;&nbsp;&nbsp;
                            <button type="submit"
                                    formaction="./AddToCartServlet?uid=<%=username%>&pid=<%=product.getProductId()%>&pqty=1&action=buy"
                                    formaction="cartDetails.jsp"
                                    class="btn btn-primary">Buy Now</button>
                            <%
                            } else {
                            %>
                            <button type="submit"
                                    formaction="./AddToCartServlet?uid=<%=username%>&pid=<%=product.getProductId()%>&pqty=0"
                                    class="btn btn-danger">Remove From Cart</button>
                            &nbsp;&nbsp;&nbsp;
                            <button type="submit" formaction="./CartDetailServlet"
                                    class="btn btn-success">Checkout</button>
                            <%
                                }
                            %>
                        </form>
                        <br />
                    </div>
                </div>

                <%
                    }
                %>

            </div>
        </div>
        <!-- ENd of Product Items List -->


        <%@ include file="footer.jsp"%>

    </body>
</html>

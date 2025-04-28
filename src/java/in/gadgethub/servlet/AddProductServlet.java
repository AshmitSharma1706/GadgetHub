/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.servlet;

import in.gadgethub.dao.impl.ProductDaoImpl;
import in.gadgethub.pojo.ProductPojo;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 161777215)
public class AddProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        String userType = (String) session.getAttribute("usertype");
        if (userType == null || !userType.equalsIgnoreCase("admin")) {
            response.sendRedirect("login.jsp?message=Access denied ! Please login as admin");
        } else if (userName == null || password == null) {
            response.sendRedirect("login.jsp?message=Session expired ! Please login again");
        }
        RequestDispatcher rd=null;
        String status="Product registration failed..!";
        String productName=request.getParameter("name");
        String productType=request.getParameter("type");
        String productInfo=request.getParameter("info");
        double productPrice=0.0;
        int productQty=0;
        String price=request.getParameter("price");
        if(price!=null){
            try{
                productPrice=Double.parseDouble(price);
            }
            catch(NumberFormatException ex){
                status="Invalid unit price..!";
                request.setAttribute("message", status);
                rd=request.getRequestDispatcher("addProduct.jsp");
                rd.forward(request, response);
                return;
            }
        }
        else{
            status="Price cannot be left blank..!";
            request.setAttribute("message", status);
            rd=request.getRequestDispatcher("addProduct.jsp");
            rd.forward(request, response);
            return;
        }
        String qty=request.getParameter("quantity");
        if(qty!=null){
            try{
                productQty=Integer.parseInt(qty);
            }
            catch(NumberFormatException ex){
                status="Invalid Quantity..!";
                request.setAttribute("message", status);
                rd=request.getRequestDispatcher("addProduct.jsp");
                rd.forward(request, response);
                return;
            }
        }
        else{
            status="Quantity cannot be left blank..!";
            request.setAttribute("message", status);
            rd=request.getRequestDispatcher("addProduct.jsp");
            rd.forward(request, response);
            return;
        }
        Part part=request.getPart("image");
        InputStream img=part.getInputStream();
        ProductDaoImpl productDao=new ProductDaoImpl();
        ProductPojo productPojo=new  ProductPojo(null,productName,productType,productInfo,productPrice,productQty,img);
        status=productDao.addProduct(productPojo);
        request.setAttribute("message", status);
        rd=request.getRequestDispatcher("addProduct.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

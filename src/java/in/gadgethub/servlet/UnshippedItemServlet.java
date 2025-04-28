/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.servlet;

import in.gadgethub.dao.OrderDao;
import in.gadgethub.dao.TransactionDao;
import in.gadgethub.dao.UserDao;
import in.gadgethub.dao.impl.OrderDaoImpl;
import in.gadgethub.dao.impl.TransactionDaoImpl;
import in.gadgethub.dao.impl.UserDaoImpl;
import in.gadgethub.pojo.OrderPojo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ashmi
 */
public class UnshippedItemServlet extends HttpServlet {

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
        TransactionDao transactionDao=new TransactionDaoImpl();
        UserDao userDao=new UserDaoImpl();
        OrderDao orderDao=new OrderDaoImpl();
        Map<String, String> user_id=new HashMap<>();
        Map<String, String> user_address=new HashMap<>();
        List<OrderPojo> orders=orderDao.getAllOrders();
        for(OrderPojo order:orders){
            String transId=order.getOrderId();
            String userId=transactionDao.getUserId(transId);
            user_id.put(transId, userId);
            String userAddress=userDao.getUserAddress(userId);
            user_address.put(userId, userAddress);
        }
        RequestDispatcher rd=request.getRequestDispatcher("unshippedItems.jsp");
        request.setAttribute("orders", orders);
        request.setAttribute("user_id", user_id);
        request.setAttribute("user_address", user_address);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.servlet;

import in.gadgethub.dao.UserDao;
import in.gadgethub.dao.impl.UserDaoImpl;
import in.gadgethub.pojo.UserPojo;
import in.gadgethub.utility.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ashmi
 */
public class RegisterServlet extends HttpServlet {

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
        String username = request.getParameter("username").trim();
        String useremail = request.getParameter("useremail").trim();
        String address = request.getParameter("address").trim();
        String mobile=request.getParameter("mobile").trim();
        int pincode = Integer.parseInt(request.getParameter("pincode").trim());
        String password = request.getParameter("password").trim();
        String confpassword = request.getParameter("confpassword").trim();
        RequestDispatcher rd = null;
        String status = null;
        if (username == null || useremail == null || address == null || password == null || 
                confpassword == null || mobile == null || request.getParameter("pincode").trim() == null) {
            status = "Feilds cannot left blank. Please input valid data..!";
            rd = request.getRequestDispatcher("register.jsp");
            request.setAttribute("message", status);
            rd.forward(request, response);
        } else if (!Validation.isValidEmail(useremail)) {
            status = "Invalid email. Please enter email in correct format..!";
            rd = request.getRequestDispatcher("register.jsp");
            request.setAttribute("message", status);
            rd.forward(request, response);
        } else if (!Validation.isValidMobile(mobile)) {
            status = "Invalid mobile number. Please enter valid mobile number..!";
            rd = request.getRequestDispatcher("register.jsp");
            request.setAttribute("message", status);
            rd.forward(request, response);
        } else if(!password.equals(confpassword)){
            status = "Confirm Password does not match with Password. Please enter same password..!";
            rd = request.getRequestDispatcher("register.jsp");
            request.setAttribute("message", status);
            rd.forward(request, response);
        }else{
            UserDao userDao=new UserDaoImpl();
            UserPojo user=new UserPojo();
            user.setUsername(username);
            user.setUseremail(useremail);
            user.setAddress(address);
            user.setMobile(mobile);
            user.setPincode(pincode);
            user.setPassword(password);
            status=userDao.registerUser(user);
            rd = request.getRequestDispatcher("register.jsp");
            request.setAttribute("message", status);
            rd.forward(request, response);
        }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.servlet;

import com.bank.domain.Customer;
import com.bank.domain.DataConnection;
import com.bank.domain.User;
import com.bank.domain.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String a = request.getParameter("account");
         int account=Integer.parseInt(a);
         String email = request.getParameter("email");
         String phone = request.getParameter("phone");
         
         UserDao userDao = DataConnection.getUserDao();
         Customer cstm = new Customer(account, "", email, phone, 0);
        //Using Java Beans - An easiest way to play with group of related data
         
         String customerValidate=userDao.authenticateCustomer(cstm);//The core Logic of the Registration application is present here. We are going to insert user data in to the database.
         if(customerValidate.equals("SUCCESS"))   //On success, you can display a message to user on Home page
         {
            Customer dbcstm = userDao.getCustomer(account);
            User u=userDao.generateCredentials(dbcstm);       //generating login id and password
            userDao.newUser(u); //updating user database
            request.setAttribute("signup","Sign up successful.An email will be sent on registered email ID after approval.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
         }
         else   //On Failure, display a meaningful message to the User.
         {
            request.setAttribute("errMessage", customerValidate);
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
         }
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

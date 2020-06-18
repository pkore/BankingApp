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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "forgotDetailsServlet", urlPatterns = {"/forgotDetailsServlet"})
public class forgotDetailsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String account=request.getParameter("account");
        UserDao userdao=DataConnection.getUserDao();
        Customer cstm =userdao.getCustomer(Integer.parseInt(account));
        User user = userdao.getUser(Integer.parseInt(account));
        if(cstm.getAccount() != 0 && user.getAccount() !=0){
            userdao.sendCredentials(cstm);
            request.setAttribute("errMessage","An email has been sent on registered Email ID.");
        } else{
            request.setAttribute("errMessage","No such account exists.");
        } 
        request.getRequestDispatcher("/forgotDetails.jsp").forward(request, response);//forwarding the request
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

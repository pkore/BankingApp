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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "conductTransactionServlet", urlPatterns = {"/conductTransactionServlet"})
public class conductTransactionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String value=request.getParameter("value");
      HttpSession session=request.getSession(false); 
      String login=(String)session.getAttribute("name");
      UserDao userdao=DataConnection.getUserDao();
      User user=userdao.getUser(login);
      Customer cstm=userdao.getCustomer(user.getAccount());
      double bal=cstm.getBalance();
      int balance=(int)bal;
      if(balance>Integer.parseInt(value)){
        request.setAttribute("errMessage","You will have balance:"+balance+".Press confirm to continue the transaction");
        request.getRequestDispatcher("/conductTransaction.jsp").include(request, response);
      }else{
          request.setAttribute("errMessage","You have insufficient balance");
          request.getRequestDispatcher("/conductTransaction.jsp").include(request, response);
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

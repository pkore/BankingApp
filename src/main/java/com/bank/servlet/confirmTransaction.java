/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.servlet;

import com.bank.domain.DataConnection;
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
@WebServlet(name = "confirmTransaction", urlPatterns = {"/confirmTransaction"})
public class confirmTransaction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String v=(String)request.getParameter("value");
      String d=request.getParameter("dest");
      HttpSession session=request.getSession(false); 
      String login=(String)session.getAttribute("name");
      UserDao userdao=DataConnection.getUserDao();
      int dest=Integer.parseInt(d);
      double value=Double.parseDouble(v);
      String transaction=userdao.sendMoney(login,dest,value);
      if(transaction.equals("SUCCESS")) //If function returns success string then user will be rooted to Home page
         {
            request.setAttribute("errMessage1", "Transaction sucessful"); 
            request.getRequestDispatcher("/conductTransaction.jsp").include(request, response);
         }
         else
         {
             request.setAttribute("errMessage1", transaction); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
             request.getRequestDispatcher("/conductTransaction.jsp").include(request, response);//including the request
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

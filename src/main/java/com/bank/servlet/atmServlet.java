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
@WebServlet(name = "atmServlet", urlPatterns = {"/atmServlet"})
public class atmServlet extends HttpServlet {

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession(false);
        String login=(String) session.getAttribute("name");
        UserDao userdao=DataConnection.getUserDao();
        String status=userdao.orderCard(login);
        if(status=="SUCCESS"){
            request.setAttribute("errMessage", "Requested for ATM Card");
            request.getRequestDispatcher("/checkStatusATM.jsp").forward(request, response);
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

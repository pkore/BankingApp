/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.servlet;

import com.bank.domain.DataConnection;
import com.bank.domain.User;
import com.bank.domain.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
@WebServlet(name = "statusServlet", urlPatterns = {"/statusServlet"})
public class statusServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String login=request.getParameter("log");
        UserDao userdao=DataConnection.getUserDao();
        HttpSession session=request.getSession(false);
        User u=userdao.getUser(login);
        String status=u.getCardStat();
        request.setAttribute("status", status);
        ServletContext context=getServletContext();
        RequestDispatcher dispatcher =context.getRequestDispatcher("/checkStatusATM.jsp");
        dispatcher.forward(request, response);
        //request.setAttribute("errMessage", status);
       
    }

 

}

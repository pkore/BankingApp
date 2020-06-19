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
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        UserDao userdao=DataConnection.getUserDao();
//        HttpSession session=request.getSession(false);
//        String login=(String) session.getAttribute("name");
//        User u=userdao.getUser(login);
//        String status=u.getCardStat();
//        request.setAttribute("status", status);
//        //request.setAttribute("errMessage", status);
//        ServletContext context=getServletContext();
//        RequestDispatcher dispatcher =context.getRequestDispatcher("/checkStatusATM.jsp");
//        dispatcher.forward(request, response);  
//    }
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String name = String.valueOf(request.getParameter("name"));
        UserDao userdao = DataConnection.getUserDao();
        String status = userdao.getUser(name).getCardStat();
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
            /* TODO output your page here. You may use following sample code. */
        out.println(status);
        out.close();
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
//        processRequest(request, response);
        
        String name = String.valueOf(request.getParameter("name"));
        UserDao userdao = DataConnection.getUserDao();
        String status = userdao.getUser(name).getCardStat();
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
            /* TODO output your page here. You may use following sample code. */
        out.println(status);
        out.close();

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
//        processRequest(request, response);
        String name = String.valueOf(request.getParameter("name"));
        UserDao userdao = DataConnection.getUserDao();
        String status = userdao.getUser(name).getCardStat();
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
            /* TODO output your page here. You may use following sample code. */
        out.println(status);
        out.close();
        
    }
 

}

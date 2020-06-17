/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.servlet;

import com.bank.domain.Customer;
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
@WebServlet(name = "changePasswordServlet", urlPatterns = {"/changePasswordServlet"})
public class changePasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldPass=request.getParameter("currentPass");
        String newPass=request.getParameter("newPass");
        UserDao userdao=DataConnection.getUserDao();
        HttpSession session=request.getSession(false);
        String login=(String) session.getAttribute("name");
        String status=userdao.updatePassword(login,oldPass,newPass);
        if(status=="SUCCESS"){
            request.setAttribute("errMessage", "Password updated");
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
        }else{
            request.setAttribute("errMessage", status);
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
        }
    }

    
}

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
@WebServlet(name = "changeUsernameServlet", urlPatterns = {"/changeUsernameServlet"})
public class changeUsernameServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newLogin=request.getParameter("newLogin");
        UserDao userdao=DataConnection.getUserDao();
        HttpSession session=request.getSession(true);
        String login=(String) session.getAttribute("name");
        String status=userdao.updateLogin(login,newLogin);
        if(status=="SUCCESS"){
            session.invalidate();
            session=request.getSession(true);
            session.setAttribute("name",newLogin);
            request.setAttribute("errMessage", "Username updated");
            request.getRequestDispatcher("/logoutServlet").forward(request, response);
        }else{
            request.setAttribute("errMessage", status);
            request.getRequestDispatcher("/changeUsername.jsp").forward(request, response);
        }
    }

}

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
@WebServlet(name = "adminATMServlet", urlPatterns = {"/adminATMServlet"})
public class adminATMServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDao userdao=DataConnection.getUserDao();
        HttpSession session=request.getSession(false);
        Customer cstm=(Customer) session.getAttribute("customer");
        User u=userdao.getUser(cstm.getAccount());
        if(null != u.getCardStat()){
            switch (u.getCardStat()) {
            case "ordered":{
                String status=userdao.updateCard(u.getUsername(),"Request processes");
                    break;
                }
            case "Request processes":{
                String status=userdao.updateCard(u.getUsername(),"Request accepted");
                    break;
                }
            case "Request accepted":{
                String status=userdao.updateCard(u.getUsername(),"Card dispatched");
                    break;
                }
            case "Card dispatched":{
                String status=userdao.updateCard(u.getUsername(),"Card delivered");
                    break;
                }
            default:
                break;
        }
        }
        request.getRequestDispatcher("/adminATM.jsp").forward(request, response);
    }

}

<%-- 
    Document   : searchAccountNext
    Created on : 16-Jun-2020, 6:15:30 pm
    Author     : Admin
--%>
<%@page import="com.bank.domain.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.domain.User"%>
<%@page import="com.bank.domain.Customer"%>
<%@page import="com.bank.domain.DataConnection"%>
<%@page import="com.bank.domain.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="form" onsubmit="searchAccountNext.jsp">
            Account number:<input type="number" name="account" >
            <input type="submit" >
        </form>
        <% 
            String acc=request.getParameter("account");
            int account=Integer.parseInt(acc);
            UserDao userdao=DataConnection.getUserDao();
            Customer cstm =userdao.getCustomer(account);
            double balance=cstm.getBalance();
        %>
        <ul>
            <li>account:<%= cstm.getAccount() %>  Name:<%= cstm.getName() %>  Email:<%= cstm.getEmail() %>  Contact:<%= cstm.getPhone() %>  Balance:<%= cstm.getBalance() %></li>
        </ul>
        <%
            User u=userdao.getUser(account);
            if(u.isActive()){%>
               <br><li><%= u %></li>
        <%  List<Transaction> i=u.getTransaction();
            for(Transaction t: i){
        %>
        <ul>
            <li>id:<%= t.getId() %>  source:<%= t.getSource() %>  Destination:<%= t.getDest() %>  Value:<%= t.getValue() %>  Balance:<%= balance %></li>
        </ul>
        <% 
            if(u.getAccount()==t.getSource()){
                balance=balance+t.getValue();
            }else{
                balance=balance-t.getValue();
            }};}else{%>
               Not an active user
              <% }%>
    </body>
</html>

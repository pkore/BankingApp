<%-- 
    Document   : allActiveUsers
    Created on : 16-Jun-2020, 5:24:08 pm
    Author     : Admin
--%>

<%@page import="com.bank.domain.Customer"%>
<%@page import="com.bank.domain.Transaction"%>
<%@page import="com.bank.domain.UserDao"%>
<%@page import="com.bank.domain.DataConnection"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.domain.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>All active users</h1>
        <%  UserDao userdao=DataConnection.getUserDao();
            List<User> userList = userdao.getAllUsers();
            for(User user: userList){
            if(user.isActive()){
        %>
        <br><li><%= user %></li>
        <%  List<Transaction> i=user.getTransaction();
            Customer cstm=userdao.getCustomer(user.getAccount());
            double balance=cstm.getBalance();
        %>
        <% for(Transaction t: i){
        %>
        <ul>
            <li>id:<%= t.getId() %>  source:<%= t.getSource() %>  Destination:<%= t.getDest() %>  Value:<%= t.getValue() %>  Balance:<%= balance %></li>
        </ul>
        <% 
            if(user.getAccount()==t.getSource()){
                balance=balance+t.getValue();
            }else{
                balance=balance-t.getValue();
            }};}}%>
    </body>
</html>


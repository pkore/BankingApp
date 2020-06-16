<%-- 
    Document   : UserTransac
    Created on : 16-Jun-2020, 12:59:59 am
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.bank.domain.Transaction"%>
<%@page import="com.bank.domain.User"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.domain.DataConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.bank.domain.UserDao" %>
<!DOCTYPE html>

<% 
    UserDao userdao=DataConnection.getUserDao();
    String login=(String)session.getAttribute("name");
    User u=userdao.getUser(login);
    List<Transaction> i=u.getTransaction();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello <%= u.getUsername() %></h1>
        <% for(Transaction t: i){
        %>
        <ul>
            <li><%= t %></li>
        </ul>
        <%;}%>
    </body>
</html>

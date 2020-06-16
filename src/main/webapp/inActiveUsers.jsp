<%-- 
    Document   : inActiveUsers
    Created on : 16-Jun-2020, 5:43:07 pm
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="com.bank.domain.DataConnection"%>
<%@page import="com.bank.domain.User"%>
<%@page import="com.bank.domain.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Inactive Users</h1>
        <%  
            UserDao userdao=DataConnection.getUserDao();
            List<User> userList = userdao.getAllUsers();
            for(User user: userList){
            if(!user.isActive()){
        %>
        <br><li><%= user %></li>
        <%;}}%>
    </body>
</html>

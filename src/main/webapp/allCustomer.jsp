<%-- 
    Document   : allCustomer
    Created on : 16-Jun-2020, 4:43:48 pm
    Author     : Admin
--%>

<%@page import="com.bank.domain.Customer"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.domain.DataConnection"%>
<%@page import="com.bank.domain.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    UserDao userdao=DataConnection.getUserDao();
    List<Customer> cstmList = userdao.getAllCustomers();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details of all customers</title>
        <% 
            for(Customer cstm: cstmList){
        %>
    </head>
    <body>
        <ul>
            <li>account:<%= cstm.getAccount() %>  Name:<%= cstm.getName() %>  Email:<%= cstm.getEmail() %>  Contact:<%= cstm.getPhone() %>  Balance:<%= cstm.getBalance() %></li>
        </ul>
        <%;}%>
    </body>
</html>

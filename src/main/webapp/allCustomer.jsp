<%-- 
    Document   : allCustomer
    Created on : 16-Jun-2020, 4:43:48 pm
    Author     : Admin
--%>

<%@page import="com.bank.domain.User"%>
<%@page import="com.bank.domain.Customer"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.domain.DataConnection"%>
<%@page import="com.bank.domain.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-18">
  <title>Transaction Details</title>
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
  *{
    font-family: calibri;
    font-size: 15px;
  font-weight: 600;
  }
body{
  background-color: #84b8f0;
}
p{
	text-align: center;
	font-size: 20px;
}



nav {
	margin: 27px auto 0;

	position: relative;
	width: 100px;
	height: 50px;
	background-color: #34495e;
	border-radius: 8px;
	font-size: 0;
}
nav a {
	line-height: 50px;
	height: 100%;
	font-size: 15px;
	display: inline-block;
	position: relative;
	z-index: 1;
	text-decoration: none;
	text-transform: uppercase;
	text-align: center;
	color: white;
	cursor: pointer;
}
nav .animation {
	position: absolute;
	height: 100%;
	top: 0;
	z-index: 0;
	transition: all .5s ease 0s;
	border-radius: 8px;
}

a:nth-child(1) {
	width: 100px;
}


nav .start-home, a:nth-child(1):hover~.animation {
	width: 100px;
	left: 0;
	background-color: #1abc9c;
}
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 80%;
  position: center;
}

td, th {

  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}

</style>
</head>
<body>
	<nav>
	<a href="/Bank/adminHomepage.jsp"><i class="fa fa-empire"></i></a>
	
	<div class="animation start-home"></div>
</nav>
<br>
<div class="jumbotron ">
	<p>Details of all Customers</p>
</div>
<br>
<% 
    UserDao userdao=DataConnection.getUserDao();
    List<Customer> cstmList = userdao.getAllCustomers();
%>
<table align="center">
	<
	<tr>
		<th>Name</th>
		<th>Account Number</th>
		<th>Contact Number</th>
		<th>E-mail Id</th>
		<th>Balance</th>
		<th>Netbanking status</th>
	</tr>
        <% 
            for(Customer cstm: cstmList){
            User u=userdao.getUser(cstm.getAccount());
        %>
        
	<tr>
                <th><%= cstm.getName() %></th>
                <th><%= cstm.getAccount() %></th>
		<th><%= cstm.getPhone() %></th>
		<th><%= cstm.getEmail() %></th>
		<th><%= cstm.getBalance() %></th>
		<% if(u.isActive()){%>
                    <th>Active</th>
                <% }else{%><th>Inactive</th>
                <%}%>
	</tr>
	<%;}%>




</body>
</html>
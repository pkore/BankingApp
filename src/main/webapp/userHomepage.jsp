<%-- 
    Document   : userHomepage
    Created on : 17-Jun-2020, 12:36:08 am
    Author     : Admin
--%>

<%@page import="com.bank.domain.Customer"%>
<%@page import="com.bank.domain.User"%>
<%@page import="com.bank.domain.DataConnection"%>
<%@page import="com.bank.domain.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-18">
  <title>Home Page</title>
    
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
nav {
	margin: 27px auto 0;

	position: relative;
	width: 590px;
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
a:nth-child(2) {
	width: 110px;
}
a:nth-child(3) {
	width: 100px;
}
a:nth-child(4) {
	width: 160px;
}
a:nth-child(5) {
	width: 120px;
}

nav .start-home, a:nth-child(1):hover~.animation {
	width: 100px;
	left: 0;
	background-color: #1abc9c;
}
nav .start-about, a:nth-child(2):hover~.animation {
	width: 110px;
	left: 100px;
	background-color: #1abc9c;
}
nav .start-blog, a:nth-child(3):hover~.animation {
	width: 100px;
	left: 210px;
	background-color: #1abc9c;
}
nav .start-portefolio, a:nth-child(4):hover~.animation {
	width: 160px;
	left: 310px;
	background-color: #1abc9c;
}
nav .start-contact, a:nth-child(5):hover~.animation {
	width: 120px;
	left: 470px;
	background-color: #1abc9c;
}

b

.grid-container {

  display: grid;
  grid-template-columns: auto;
  grid-gap: 15px;
  
  padding: 20px;
}

.group{
	margin-bottom: 10px;
	position: relative;
	border-bottom: 2px solid #444;
}
.group i{
	position: absolute;

	

}

.grid-container > div {
  border-radius: 25px;
  background-color: rgba(255, 255, 255, 0.8);
  text-align: center;
  padding: 20px 4;
  font-size: 35px;
}
</style>
</head>
<body>
<nav>
	<a href="#"><i class="fa fa-empire"></i></a>
	<a href="/Bank/userHomepage.jsp">Home</a>
	<a href="#">About</a>
	<a href="#">Contact Us</a>
	<a href="/Bank/logoutServlet">Logout</a>
	<div class="animation start-home"></div>
</nav>

<p></p>
<%
    UserDao userdao=DataConnection.getUserDao();
    String login=(String)session.getAttribute("name");
    User u=userdao.getUser(login);
    Customer cstm =userdao.getCustomer(u.getAccount());
%>
<div class="jumbotron ">
  <h2>User details</h2>
  <p>Account no: <%= cstm.getAccount()%></p>
  <p>Name: <%= cstm.getName() %></p>
  <p>Login ID: <%= login %></p>
  <p>E-mail: <%= cstm.getEmail()%></p>
  <p>Contact no: <%= cstm.getPhone()%></p> 
</div>


<div class="grid-container">
  <div class="group"><a href="/Bank/userTransac.jsp">Show Transactions</a></div>
  <div class="group"><a href="/Bank/conductTransaction.jsp">Make a New Transaction</a></div>
  <div class="group"><a href="#">Apply for Credit Card</a></div>
  <div class="group"><a href="#">Change Username/Password</a></div>  

</div>

</body>
</html>


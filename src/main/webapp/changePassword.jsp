<%-- 
    Document   : changePassword
    Created on : 17-Jun-2020, 10:20:16 pm
    Author     : Admin
--%>
<%@page import="com.bank.domain.UserDao"%>
<%@page import="com.bank.domain.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bank.domain.Transaction"%>
<%@page import="com.bank.domain.User"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.domain.DataConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <% 
    UserDao userdao=DataConnection.getUserDao();
    String login=(String)session.getAttribute("name");
    User u=userdao.getUser(login);
    List<Transaction> i=u.getTransaction();
    Customer cstm=userdao.getCustomer(u.getAccount());
    double balance=cstm.getBalance();
%>
<head>
	<meta charset="UTF-18">
	<title>Change Password</title>
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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


.login{
	text-align: center;
	position: relative;
	width: 280px;


}
input, button{
	width: 100%;
	border: 0;
	border-radius: 20px;
}
input{
	border-bottom: 2px solid #444;
	padding: 12px 40px 12px 20px;
}

input, button, .group i, p, a{
	font-size: 13.3333px;
	font-weight: 600;
}
.group{
	margin-bottom: 10px;
	position: relative;
}
.group i{
	position: absolute;
	top: 15px;
	right: 20px;
}
button{
	padding: 12px;
	background-color: #34495e;
	margin-bottom: 20px; 
	cursor: pointer;

}
button, button i{
	color: #fff;
}
button i{
	margin-right: 5px;
}
.wrap{
  width: 30%;
  position: absolute;
 top: 100%;
  left: 55%;
  transform: translate(-50%, -50%);
}
</style>
<script>
            function validate(){
                var confPass = document.form.confPass.value; 
                 var currentPass = document.form.currentPass.value;
                 var password=document.form.newPass.value;
                  if (confPass===null || confPass==="" || password===null || password==="" || currentPass===null || currentPass==="")
                 { 
                 alert("Fill all the details"); 
                 return false; 
                 }
                 if(confPass!==password){
                     alert("Confirm Password does not match with new password"); 
                 return false; 
                 }
            }
                 
</script>
</head>
<body>
	<nav>
	<a href="/Bank/userHomepage.jsp"><i class="fa fa-empire"></i></a>
	<a href="/Bank/userHomepage.jsp">Home</a>
	<a href="#">About</a>
	<a href="#">Contact Us</a>
	<a href="/Bank/logoutServlet">Logout</a>
	<div class="animation start-home"></div>
</nav>

<p></p>

<div class="jumbotron ">
  <h2>User details</h2>
  <p>Account no: <%= cstm.getAccount()%></p>
  <p>Name: <%= cstm.getName() %></p>
  <p>Login ID: <%= login %></p>
  <p>E-mail: <%= cstm.getEmail()%></p>
  <p>Contact no: <%= cstm.getPhone()%></p> 
</div>
<p></p>
<div class="wrap">
	<div class="login">
		<h2>Change Password</h2>                            
                <form name="form" method="post" action="changePasswordServlet" onsubmit="return validate()">
		<div class="group"><input  type="password" placeholder="Current Password" align="middle" name="currentPass"><i class="fa fa-lock"></i></i></div>
		<div class="group"><input type="password" placeholder="New Password" name="newPass"><i class="fa fa-lock"></i></div>
		<div class="group"><input type="password" placeholder="Confirm New Password" name="confPass"><i class="fa fa-lock"></i></div>
                <input type="submit" value="Confirm" />
		</form>
                <center><p style="color:green"><%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%></p></center>
      </div>
  </div>
  </body>
  </html>
 
                        
            
                           
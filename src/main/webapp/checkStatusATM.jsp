<%-- 
    Document   : checkStatusATM
    Created on : 18-Jun-2020, 4:40:51 pm
    Author     : Admin
--%>

<%@page import="com.bank.domain.DataConnection"%>
<%@page import="com.bank.domain.UserDao"%>
<%@page import="com.bank.domain.User"%>
<%@page import="com.bank.domain.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.domain.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    UserDao userdao=DataConnection.getUserDao();
    String login=(String)session.getAttribute("name");
    if(login==null || login==""){
        response.sendRedirect("login.jsp");
    }
    User u=userdao.getUser(login);
    List<Transaction> i=u.getTransaction();
    Customer cstm=userdao.getCustomer(u.getAccount());
    double balance=cstm.getBalance();
%>
<html lang="en">
<head>
	<meta charset="UTF-18">
	<title>Forgot</title>
	
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
	


}
button{
	width: 100%;
	border: 0;
	border-radius: 20px;
}

button, p, a{
	font-size: 13.3333px;
	font-weight: 600;
}

button{
	padding: 12px;
	background-color: #34495e;
	margin-bottom: 20px; 
	cursor: pointer;

}
button{
	color: #fff;
}
button i{
	margin-right: 5px;
}
.wrap{
  width: 30%;
  position: absolute;
 top: 85%;
  left: 55%;
  transform: translate(-50%, -50%);
}

</style>
<script>
function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status===200) {
      document.getElementById("status").innerHTML=this.responseText;
    }
  };
  xhttp.open("GET","/Bank/statusServlet?name=${name}", true);
  xhttp.send();
}

</script>
</head>
<body>
    <nav>
	<a href="/Bank/userHomepage.jsp"><i class="fa fa-empire"></i></a>
	<a href="/Bank/userHomepage.jsp">Home</a>
	<a href="/Bank/about.jsp">About</a>
	<a href="/Bank/contact.jsp">Contact Us</a>
	<a href="/Bank/logoutServlet">Logout</a>
	<div class="animation start-home"></div>

</nav>

<p></p>
<div class="jumbotron ">
  <p>Account no: <%= cstm.getAccount()%></p>
  <p>Name: <%= cstm.getName() %></p>
  <p>Login ID: <%= login %></p>
  <p>E-mail: <%= cstm.getEmail()%></p>
  <p>Contact no: <%= cstm.getPhone()%></p> 
</div>
<br>

<div class="wrap">
	<div class="login">
		<br>
		<p style="font-size: 30px; color: #34495e; font-family: calibri;" id="status">Application is in process...</p>
		<button type="button" onclick="loadDoc()">Refresh</button>
		
      </div>
  </div>
  </body>
  </html>


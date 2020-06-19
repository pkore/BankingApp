<%-- 
    Document   : about
    Created on : 19-Jun-2020, 12:17:34 pm
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-18">
  <title>Contact</title>
    
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



</style>
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
  
  <h2>About us</h2>
  <p> Founded in 1806, Bank of Calcutta was the first Bank established in India and over a period of time evolved into New Montreal Bank. NMB represents a sterling legacy of over 200 years. It is the oldest commercial Bank in the Indian subcontinent, strengthening the nationâ€™s trillion-dollar economy and serving the aspirations of its vast population.

The  New Montreal Bank largest commercial Bank in terms of assets, deposits, branches, number of customers and employees, enjoying the continuing faith of millions of customers across the social spectrum.

NMB headquartered at Mumbai, provides a wide range of products and services to individual customers, commercial enterprises, large corporates, public bodies and institutional customers through its various branches and outlets, joint ventures and subsidiaries.

</p>
  
</div>



</body>
</html>

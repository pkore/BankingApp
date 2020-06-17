<%-- 
    Document   : login
    Created on : 13-Jun-2020, 8:32:28 pm
    Author     : Admin
--%>
<%@page import="com.bank.servlet.LoginServlet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-18">
	<title>Login Page</title>
	<link rel="stylesheet" href="style.css">
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script> 
            function validate()
            { 
                 var username = document.form.Username.value; 
                 var password = document.form.Password.value;

                 if (username==null || username=="")
                 { 
                 alert("Username cannot be blank"); 
                 return false; 
                 }
                 else if(password==null || password=="")
                 { 
                 alert("Password cannot be blank"); 
                 return false; 
                 } 
            }
    </script> 
</head>
<body>
	<div class="login">
		<i class="fa fa-empire"></i>
		<h2>Login</h2>
                <form name="form" action="LoginServlet" method="post" onsubmit="return validate()">
		<div class="group"><input type="text" placeholder="Username" name="Username"><i class="fa fa-user-circle" aria-hidden="true"></i></i></div>
		<div class="group"><input type="password" placeholder="Password" name="Password"><i class="fa fa-lock"></i></div>
		<p style="color:red"><%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%></p>
                <button><i class="fa fa-send"></i>Login</button>
                </form>
		<p class="fs">Forgot <a href="#">Username</a> / <a href="#">Password</a>?</p>
                <p class='ss'>Don't have an account?    <a href="/Bank/signup.jsp">Signup</a></p>
                
      </div>
  </body>
  </html>

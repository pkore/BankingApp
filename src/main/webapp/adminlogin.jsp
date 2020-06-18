<%-- 
    Document   : adminlogin
    Created on : 18 Jun 2020, 22:16:05
    Author     : Anushka Chincholkar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-18">
	<title>Admin Login Page</title>
	<link rel="stylesheet" href="style.css">
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="login">
		<i class="fa fa-empire"></i>
		<h2>Admin Login</h2>
                <form name="form" action="j_security_check" method="POST">
		<div class="group"><input type="text" placeholder="Username" name="j_username"><i class="fa fa-user-circle" aria-hidden="true"></i></i></div>
                <div class="group"><input type="password" placeholder="Password" name="j_password"><i class="fa fa-lock"></i></div>
                <button><i class="fa fa-send"></i>Login</button>
                </form>
                
      </div>
  </body>
  </html>

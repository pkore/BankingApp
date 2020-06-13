<%-- 
    Document   : signup
    Created on : 13-Jun-2020, 8:33:15 pm
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-18">
	<title>Signup Page</title>
	<link rel="stylesheet" href="style.css">
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="login">
		<i class="fa fa-empire"></i>
		<h2>Signup</h2>
		<div class="group"><input type="number" placeholder='Account Number'><i class="fa fa-id-badge" aria-hidden="true"></i></div>
		<div class="group"><input type="text" placeholder='Name'><i class="fa fa-user-circle" aria-hidden="true"></i></i></div>
		<div class="group"><input type="number" id="phone" placeholder="Phone" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}"><i class="fa fa-phone" aria-hidden="true"></i></div>
		<div class="group"><input type="email" placeholder="E-mail"><i class="fa fa-info-circle" aria-hidden="true"></i></div>
		<button><i class="fa fa-send"></i>Submit</button>
		
      </div>
  </body>
  </html>

<%-- 
    Document   : conductTransaction
    Created on : 16-Jun-2020, 6:45:19 pm
    Author     : Admin
--%>

<%@page import="com.bank.domain.Customer"%>
<%@page import="com.bank.domain.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.domain.User"%>
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
select{
	background-color: #84b8f0;
}

table { 
                border-collapse: collapse; 
            } 
          
            th, td { 
                width:200px; 
                text-align:center; 
                
                padding:5px 
             }

button{
		width: 150px;
	border: 0;
	border-radius: 20px;
	padding: 12px;
	background-color: #1abc9c;
	margin-bottom: 20px; 
	cursor: pointer;
}
input{
	background-color: #fff;
    width: 150px;
    font-size: 12px;
	border-radius: 20px;
	border: 0;
	text-align: center;
}
</style>
<script>
                 
    function onlynum() { 
        var tag = document.getElementById("account"); 
        var res = tag.value; 
                
        if (res != '') { 
            if (isNaN(res)) { 
                  alert("Invalid input.");
                // Set input value empty 
                ip.value = ""; 
                  
                // Reset the form 
                fm.reset();
                 
                return false; 
            } else { 
                return true 
            } 
        } 
    } 
    
    function change(){
        var account = document.form.dest.value;
        var value = document.form.value.value;
        if(account==null || account=="" || value==null || value==""){
            alert("Fill all the details.");
        }
        if (account.length!=6)
                 { 
                 alert("Provide a 6 digit numeric bank account number."); 
                 return false; 
                 }else{
                     document.getElementById("b2").style.visibility="visible";
                 }
        return true;
    }
</script>
</head>
<body>
    <% 
    UserDao userdao=DataConnection.getUserDao();
    String login=(String)session.getAttribute("name");
    User u=userdao.getUser(login);
    List<Transaction> i=u.getTransaction();
    Customer cstm=userdao.getCustomer(u.getAccount());
    double balance=cstm.getBalance();
%>
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
  <p>Balance:<%= cstm.getBalance() %></p>
</div>

<p></p>
<h2 align="center">Initiate Simple Payment</h2>
<form name="form" method="post" action="confirmTransaction">
<table align="center">
	<tr>
		<th>Destination Account Number: </th>
		<th><input type="number" placeholder="Enter Account No here" maxlength="6" name="dest" oninput="return onlyNum()"></th>
	</tr>
	<br>
	<tr>
		<th>Amount: </th>
		<th><input type="number" placeholder="Enter Amount here" name="value" oninput="return onlyNum()"></th>
	</tr>
	
	<tr>
            <th><button type="button" onclick="return change()">Proceed</button></th>
            <th><input type="submit" id="b2"  style="visibility:hidden" value="confirm" /></th>
  
</table>
        <center><%=(request.getAttribute("errMessage1") == null) ? "": request.getAttribute("errMessage1")%></center>
</form>

</body>
</html>

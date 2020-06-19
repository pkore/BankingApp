<%-- 
    Document   : searchAccountNext
    Created on : 16-Jun-2020, 6:15:30 pm
    Author     : Admin
--%>
<%@page import="com.bank.domain.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="com.bank.domain.User"%>
<%@page import="com.bank.domain.Customer"%>
<%@page import="com.bank.domain.DataConnection"%>
<%@page import="com.bank.domain.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-18">
  <title>Account Details</title>
    
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



.search {  width: 100%; 
 
  display: flex;}
.searchTerm {  width: 50%;  
	border: 1px solid #84b8f0;  
	border-right: none;  
	padding: 5px; 
    height: 40px;  
    outline: none;  
    color: #9DBFAF;

}
   .searchTerm:focus{  
   	color: black;
   }
   	.searchButton {  width: 40px;  height: 40px;  border: 1px solid #84b8f0;  background: #84b8f0;  text-align: center;  color: #fff;  cursor: pointer;  
   		font-size: 20px;}


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

select{
	background-color: #84b8f0;
}

.wrap{
  width: 30%;
  position: absolute;
 
  left: 55%;
  transform: translate(-50%, -50%);
}

</style>
<script>
function validate(){
    var account = document.form.account.value;
                if (account.length!=6)
                 { 
                 alert("Provide a 6 digit bank account number."); 
                 return false; 
                 }
}    
function onlynum() { 
         
        var tag = document.getElementById("account"); 
        var res = tag.value; 
  
        if (res != '') { 
            if (isNaN(res)) { 
                  
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
</script>
</head>
<body>
<nav>
	<a href="/Bank/adminHomepage.jsp"><i class="fa fa-empire"></i></a>
	
	<div class="animation start-home"></div>
</nav>

<p></p>
<div class="jumbotron ">
	<div class="wrap">
   <div class="search">
       <form name="form" action="/Bank/searchAccountNext.jsp" onsubmit="return validate()">
      <input type="number" class="searchTerm" placeholder="Account Number" name="account" id="account" oninput="return onlynum()">
      <button type="submit" onclick="return validate()" class="searchButton">
        <i class="fa fa-search"></i>
     </button>
      </form>
   </div>
</div>
    <% 
            String acc=request.getParameter("account");
            int account=Integer.parseInt(acc);
            UserDao userdao=DataConnection.getUserDao();
            Customer cstm =userdao.getCustomer(account);
            if(cstm.getAccount()!=0){
            double balance=cstm.getBalance();
            User u=userdao.getUser(account);
    %>
<br>
  <h2>User details</h2>
  <p>Account no:<%= cstm.getAccount() %> </p>
  <p>Name:<%= cstm.getName() %> </p>
  <% if(u.isActive()){ %>
  <p>Login ID:<%= u.getUsername() %> 
  <%}else{%>Not an active Netbanking User</p><%}%>
  <p>E-mail:<%= cstm.getEmail() %> </p>
  <p>Contact no: <%= cstm.getPhone() %></p> 
  <p>Balance:<%= cstm.getBalance() %></p>
</div>

<p></p>

     <% if(u.isActive()){ %>         
     <table align="center">
     
	<tr>
		<th>Transaction ID</th>
		<th>Transaction Account</th>
		<th>Credit Amount</th>
		<th>Debit Amount</th>
		<th>Balance</th>
	</tr>
        <% 
            List<Transaction> i=u.getTransaction();
            for(Transaction t: i){
        %>
	<tr>
            <th><%= t.getId() %> </th>
	<%
            if(u.getAccount()==t.getSource()){%>
                <th><%= t.getDest() %></th>
                <th>-</th>
                <th><%= t.getValue() %></th>
                <th><%= balance%><th>
                <%
                    balance=balance+t.getValue(); 
            }else{%>
                <th><%= t.getSource() %></th>
                <th><%= t.getValue() %></th>
                <th>-</th>
                <th><%= balance%><th>
        </tr>
            <%   balance=balance-t.getValue();
            };}%>
                <tr>
            <th>-</th>
            <th>-</th>
            <th>-</th>
            <th>-</th>
            <th><%= balance %></th>
            </tr>
            <%;}}else{%>
            <div class="jumbotron ">
	<div class="wrap">
            <div class="search">
                <p style="color:red">Invalid account number</p>
            </div></div>
            
            <%}%>
	
</body>
</html>

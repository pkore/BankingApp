<%-- 
    Document   : searchAccount
    Created on : 16-Jun-2020, 6:00:53 pm
    Author     : Admin
--%>



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
select{
	background-color: #84b8f0;
}

.wrap{
  width: 30%;
  position: absolute;
 
  left: 55%;
  transform: translate(-50%, -50%);
}
p{
	text-align: center;
	font-size: 20px;
}

</style>
<script>
    document.getElementById("c").onclick = function() {validate()};
function validate(){
    var account = document.form.account.value;
                if (account.length!=6)
                 { 
                 alert("Provide a 6 digit bank account number."); 
                 return false; 
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
	<p>Enter Account Number to get Details</p>
	<br>
	<div class="wrap">
   <div class="search">
       <form name="form" method="post" action="/Bank/searchAccountNext.jsp">
      <input type="number" class="searchTerm" placeholder="Enter Account Number" name="account">
      <button type="submit" id="c" class="searchButton">
      <i class="fa fa-search"></i>
      </button>
       </form>>
   </div>
</div>

</body>
</html>

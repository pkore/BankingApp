<%-- 
    Document   : adminHomepage
    Created on : 17-Jun-2020, 2:01:41 am
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-18">
  <title>Admin Home page</title>
    
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
h2{
	text-align: center;
	font-size: 50px;
}


.grid {
  		overflow: hidden;
			padding: 0.5em 0 0 0.5em;
			max-width: 76em;
			margin: 0 auto;
		}
		.grid li {
			padding: 0 0.5em 0.5em 0;
		}
		.grid li > div {
			background: #84b8f0;
			padding: 7em 2em;
			text-align: center;
		}
		.grid li a {
			color: black;
		}
		
		@media all and (min-width: 27em) {
			.grid li {
				width: 50%;
				float: left;
			}
		}
		
		@media all and (min-width: 40em) {
			.grid li {
				width: 33.3333333%;
			}
			.grid li.wide {
				width: 66.666666%;
			}
		}
		@media all and (min-width: 60em) {
			.grid li {
				width: 25%;
			}
			.grid li.wide {
				width: 50%;
			}
		}


</style>
</head>
<body>
<nav>
	<a href="/Bank/adminHomepage.jsp"><i class="fa fa-empire"></i></a>
	
	<div class="animation start-home"></div>
</nav>

<p></p>
<div class="jumbotron ">

  <h2>Welcome Admin</h2>

</div>

<p></p>
<section id="pattern" class="pattern">
  	<ul class="grid">
			<li>
				<div>
					<a href="/Bank/searchAccount.jsp">Search Account Details</a>
				</div>
			</li>
			<li>
				<div>
					<a href="/Bank/allCustomer.jsp">View Details of all Customers</a>
				</div>
			</li>
			<li>
				<div>
					<a href="/Bank/allActiveUsers.jsp">View Details of all Active Customers</a>
				</div>
			</li>
			<li>
				<div>
					<a href="/Bank/inActiveUsers.jsp">Approve Inactive Users</a>
				</div>
				</li>
			</ul>
		</section>



</body>
</html>


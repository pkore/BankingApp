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
        <script> 
            function validate()
            { 
                 var account = document.form.account.value;
                 var email = document.form.email.value;
                 var phone = document.form.phone.value; 
                 
                 if (account==null || account=="")
                 { 
                 alert("Account number can't be blank."); 
                 return false; 
                 }
                 else if (phone==null || phone=="")
                 { 
                 alert("Contact number can't be blank"); 
                 return false; 
                 }
                 else if(phone.length<10)
                 { 
                 alert("Give valid 10 digit phone number."); 
                 return false; 
                 } 
                 else if (email==null || email=="")
                 { 
                 alert("Email can't be blank."); 
                 return false; 
                 } 
             } 
</script>
</head>
<body>
	<div class="login">
		<i class="fa fa-empire"></i>
		<h2>Signup</h2>
                <form name="form" action="RegisterServlet" method="post" onsubmit="return validate()">
		<div class="group"><input type="number" placeholder='Account Number' name="account"><i class="fa fa-id-badge" aria-hidden="true"></i></div>
		<div class="group"><input type="number" id="phone" placeholder="Contact Number" name="phone" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}"><i class="fa fa-phone" aria-hidden="true"></i></div>
		<div class="group"><input type="email" placeholder="E-mail" name="email"><i class="fa fa-info-circle" aria-hidden="true"></i></div>
		<%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%>
                <button><i class="fa fa-send"></i>Submit</button>
		</form>
      </div>
  </body>
  </html>

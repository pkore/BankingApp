<%-- 
    Document   : forgotDetails
    Created on : 18-Jun-2020, 2:15:48 am
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-18">
	<title>Forgot Details</title>
	<link rel="stylesheet" href="style.css">
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script>
    <script>
    //document.getElementById("c").onclick = function() {validate()};
function validate(){
    var account = document.form.account.value;
                if (account.length!=6)
                 { 
                 alert("Provide a 6 digit numeric bank account number."); 
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
    </script>
</head>
<body>
	<div class="login">
		<i class="fa fa-empire"></i>
		<h2>Forgot Username/Password</h2>
                <form name="form" method="post" action="forgotDetailsServlet" onsubmit="return validate()">
		<div class="group"><input type="number" placeholder='Enter Your Account No' name="account" oninput="return onlyNum()"><i class="fa fa-user-circle" aria-hidden="true"></i></i></div>
		<button type="submit"><i class="fa fa-send"></i>Confirm</button>
		</form>
                <p style="color:green"><%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%></p>
      </div>
  </body>
  </html>

<%-- 
    Document   : checkStatusATM
    Created on : 18-Jun-2020, 4:40:51 pm
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String login=(String) session.getAttribute("name");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            var log;
function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
      alert("called");
    if (this.readyState === 4 && this.status===200) {
      document.getElementById("status").innerHTML=this.responseText;
      alert("inserted");
    }
    alert("if");
  };
  alert("last");
  xhttp.open("GET","/Bank/statusServlet", true);
  xhttp.send();
}

</script>
    </head>
    <body>
        Status:<p style="color:red" id="status"><%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%></p>
        <button type="button" onclick="loadDoc()">refresh</button>
    </body>
</html>



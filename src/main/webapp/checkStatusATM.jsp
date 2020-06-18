<%-- 
    Document   : checkStatusATM
    Created on : 18-Jun-2020, 4:40:51 pm
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
function loadDoc() {
  var xhttp = new XMLHttpRequest();
  
  xhttp.open("GET", "/Bank/statusServlet.java", true);
  xhttp.send();
}
</script>
    </head>
    <body>
        Status:<p style="color:red"><%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%></p>
        <button type="button" onclick="loadDoc()">refresh</button>
    </body>
</html>



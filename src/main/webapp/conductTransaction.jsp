<%-- 
    Document   : conductTransaction
    Created on : 16-Jun-2020, 6:45:19 pm
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            func demoshow(){
                document.getElementById("test").style.visibility="visible";
            }
            </script>
    </head>
    <body>
        <form name="form" method="post" action="conductTransactionServlet" >
        Receiver:<input type="number" name="dest">
        Value:<input type="number" name="value">
        <input type="submit" value="proceed" onclick="demoshow()">
        </form>
        <%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%>
        <form action="confirmTransaction" method="post">
        <input type="submit" id="p" value="confirm" visibility="hidden">
        <%=(request.getAttribute("errMessage1") == null) ? "": request.getAttribute("errMessage1")%>
        </form>
    </body>
</html>
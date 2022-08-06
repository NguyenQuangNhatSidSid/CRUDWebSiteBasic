<%-- 
    Document   : login
    Created on : Mar 11, 2022, 9:02:11 PM
    Author     : hd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
            <h1>Input your Information: </h1>
        <form action="MainController" method="POST">
            User ID <input type="text" name="userID" required="" placeholder=""/><br><br>
            Password <input type="password" name="password" required="" placeholder=""/><br><br>
            <input type="submit" name="action" value="Login"/>
            <input type="reset" value="Reset"/>
        </form>
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>
        <%= error %>
         <a href="createPage.jsp">Create New Food</a>
    </body>
</html>

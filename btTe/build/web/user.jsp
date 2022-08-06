<%-- 
    Document   : user
    Created on : Feb 15, 2022, 9:20:52 AM
    Author     : Cò Con
--%>

<%@page import="pe.user.UserDTO"%>
<%@page import="pe.user.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <h1>User Page</h1>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        User ID:<%= loginUser.getUserID()%></br>
        Full Name:<%= loginUser.getFullName()%></br>
        Role ID:<%= loginUser.getRoleID()%></br>
        Password:<%= "***" %><br><br>
        <form action="MainController" method="POST">
            <input type="submit" name="action" value="Logout"/>
        </form>
        
</html>

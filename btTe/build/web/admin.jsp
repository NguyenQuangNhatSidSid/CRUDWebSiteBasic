<%-- 
    Document   : admin
    Created on : Mar 1, 2022, 8:29:12 PM
    Author     : hd
--%>

<%@page import="pe.food.FoodDTO"%>
<%@page import="java.util.List"%>
<%@page import="pe.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
         <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("AD")) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        WelCome: <h2><%= loginUser.getFullName()%></h2>
        
        <form action="MainController" method="POST">
        <input type="submit" name="action" value="Logout"/>
        </form></br>
        <form action="MainController">
            Search<input type="text" name="search" value="<%= search%>" placeholder="search"/>
            <input type="submit" name="action" value="Search"/><br><br>
        </form>
        <%
            List<FoodDTO> listFood = (List<FoodDTO>) request.getAttribute("LIST_FOOD");
            if (listFood != null && listFood.size() > 0) {
               
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Cooking Time</th>
                    <th>Status</th>
                    <th>DELETE</th>
                     <th>UPDATE</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (FoodDTO pro : listFood) {
                %>
            <form action="MainController">
                <tr>
                    <td><%=count++%></td>
                    <td><input type="text" name="id" value="<%=pro.getId()%>" readonly="" </td>
                    <td><input type="text" name="name" value="<%= pro.getName()%>" required="" </td>
                    <td><input type="text" name="description" value="<%= pro.getDescription()%>" required="" </td>
                    <td><input type="text" name="price" value="<%= pro.getPrice()%>" required="" required=""</td>
                    <td><input type="text" name="cookingTime" value="<%= pro.getCookingTime()%>" required="" </td>
                    <td><input type="text" name="status" value="<%= pro.isStatus()%>" required=""</td>
                    <td>
                        <a href="MainController?action=Delete&id=<%=pro.getId() %>&search=<%= search%>">Delete</a>
                        <input type="hidden" name="search" value="<%= search%>"/>
                    </td>
                    <td> 
                        <input type="submit" name="action" value="Update"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                    </td>
                    
                </tr>
            </form>

            <%
                }
            %>
        </tbody>
    </table>
    <%
        String error = (String) request.getAttribute("ERROR");
        if (error == null) {
            error = "";
        }
    %>
    <%=error%>
    <%
            
        }
    %>
    </body>
</html>

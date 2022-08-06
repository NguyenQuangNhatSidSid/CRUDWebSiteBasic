<%-- 
    Document   : createPage
    Created on : Jul 15, 2022, 8:46:36 PM
    Author     : Admin
--%>

<%@page import="pe.food.FoodError"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <%
            FoodError userError = (FoodError)request.getAttribute("FOOD_ERROR");
            if(userError==null){
                userError=new FoodError();
            }
            %>
        <form action="MainController">
            User ID <input type="text" name="id" required="" />
            <%= userError.getId()%></br>
            
            Full Name <input type="text" name="name" required="" />
            <%= userError.getName()%></br>
            
            Description <input type="text" name="description"  required="" />
            <%= userError.getDescription()%>
            </br>
            
            Price <input type="number" name="price"  required="" />
            </br>
            
            CookingTime <input type="number" name="cookingTime"  required="" />
            
            </br>
           
            
            Status <input type="text" name="status" value="true" readonly="" />
           
            </br>
            
            
            <input type="submit" name="action" value="Create" />
            <input type="reset" value="Reset" />
            
        </form>
    </body>
</html>

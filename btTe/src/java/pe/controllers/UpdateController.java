/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.food.FoodDAO;
import pe.food.FoodDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private static final String ERROR = "admin.jsp";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR;
        try {
            String ID = request.getParameter("id");
            String Name = request.getParameter("name");
            String Description = request.getParameter("description");
            float Price  = Float.parseFloat(request.getParameter("price"));
            int CookingTime=Integer.parseInt(request.getParameter("cookingTime"));
            boolean Status= Boolean.parseBoolean(request.getParameter("status"));
            FoodDAO dao= new FoodDAO();
            boolean check=dao.update(new FoodDTO(ID, Name, Description, Price, CookingTime, Status));
             HttpSession session = request.getSession();
             FoodDTO food=(FoodDTO) session.getAttribute("LIST_FOOD");
                            
            if(food.getId().equals(ID)){
                food.setName(Name);
                food.setDescription(Description);
                food.setPrice(Price);
                food.setCookingTime(CookingTime);
                food.setStatus(Status);
                session.setAttribute("LIST_FOOD", food);
            }else if(check){
                url=SUCCESS;
            }
             
        } catch (Exception e) {
            log("Error at UpdateController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

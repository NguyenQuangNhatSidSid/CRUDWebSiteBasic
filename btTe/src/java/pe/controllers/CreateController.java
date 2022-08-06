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
import pe.food.FoodDAO;
import pe.food.FoodDTO;
import pe.food.FoodError;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR = "createPage.jsp";
    private static final String SUCCESSS = "login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      String url = ERROR;
        FoodError foodEror = new FoodError();
        try {
            String ID = request.getParameter("id");
            String Name = request.getParameter("name");
            String Description = request.getParameter("description");
            float Price  = Float.parseFloat(request.getParameter("price"));
            int CookingTime=Integer.parseInt(request.getParameter("cookingTime"));
            boolean Status= Boolean.parseBoolean(request.getParameter("status"));

             FoodDAO dao= new FoodDAO();
              boolean checkValidation = true;

            if (ID.length() > 10 || ID.length() < 3) {
                foodEror.setId("Length must be in  [3,10]");
                checkValidation = false;
            }
            boolean checkDup = dao.checkDup(ID);
            if (checkDup == false) {
                foodEror.setId("duplicate");
                checkValidation = false;
            }
            if (Name.length() > 50 || Name.length() < 2) {
                foodEror.setName("Length must be in  [2,50]");
                checkValidation = false;
            }
            
            //kiem tra data da validation(all data)
            if (checkValidation) {
                FoodDTO dto = new FoodDTO(ID, Name, Description, Price, CookingTime, Status);
                boolean checkCreate = dao.create(dto);
                if (checkCreate) {
                    url = SUCCESSS;
                    
                    
                }
            } else {
                request.setAttribute("FOOD_ERROR", foodEror);
            }

        } catch (Exception e) {
            log("Error at Create Controller" + e.toString());
            if (e.toString().contains("duplicate")) {
                foodEror.setId("TRung id roi kia!");
                request.setAttribute("FOOD_ERROR", foodEror);
            }
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

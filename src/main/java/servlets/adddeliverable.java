/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.CassandraHosts;
import models.ModuleModel;

/**
 *
 * @author peterbennington
 */
@WebServlet(name = "AddDeliverable", urlPatterns = {"/AddDeliverable"})
public class adddeliverable extends HttpServlet {
    
    Cluster cluster = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        
        cluster = CassandraHosts.getCluster();
    }
    
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
        response.sendRedirect("adddeliverable.jsp");
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
        // declare variables to store the values submitted via the form
        String moduleCode = request.getParameter("moduleCode");
        String deliverableName = request.getParameter("deliverableName");
        String dueDate = request.getParameter("dueDate");
        String percentageWorth = request.getParameter("percentageWorth");
        String percentageAchieved = request.getParameter("percentageAchieved");
        
        String username = request.getParameter("username");

        
        //below is an if statement that will check none of the fields have been left empty
        if (moduleCode.equals("") || deliverableName.equals("") || dueDate.equals("") || percentageWorth.equals("") || percentageAchieved.equals("")) {
            incompleteError(request, response); // display error message
        }

        // this if statement prevents the user from creating an account that does not have all required fields completed
        if (!moduleCode.equals("") && !deliverableName.equals("") && !dueDate.equals("") && !percentageWorth.equals("") && !percentageAchieved.equals("") && !username.equals("")){
            ModuleModel del = new ModuleModel();
            del.setCluster(cluster);
            
            double perWorth = Double.parseDouble(percentageWorth);
            double perAchieved = Double.parseDouble(percentageAchieved);
            
            try {
                if (del.addDeliverable(moduleCode, deliverableName, dueDate, perWorth, perAchieved, username)) {
                    RequestDispatcher rd = request.getRequestDispatcher("adddeliverable.jsp");
                    request.setAttribute("deliverableAdded", deliverableName); // display confirmation message that module was added
                    rd.forward(request, response);
                } else {
                    
                    RequestDispatcher rd = request.getRequestDispatcher("adddeliverable.jsp");
                    request.setAttribute("deliverableExists", deliverableName); // display error message that the module already exists, therefore not added
                    rd.forward(request, response);
                }
            } catch (ParseException ex) {
                Logger.getLogger(adddeliverable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.sendRedirect("/SaveTheSemester/index.jsp");
    }

    private void incompleteError(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String incompleteError = "Not all fields have been completed. Please answer all fields before clicking <b>Add Deliverable</b>";
        RequestDispatcher rd = request.getRequestDispatcher("adddeliverable.jsp");
        request.setAttribute("incompleteError",incompleteError); // display confirmation message that deliverable was added
        rd.forward(request, response);
    }
}

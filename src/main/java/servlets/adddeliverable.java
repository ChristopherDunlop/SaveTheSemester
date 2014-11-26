/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "adddeliverable", urlPatterns = {"/adddeliverable"})
public class adddeliverable extends HttpServlet {
    
    Cluster cluster = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        
        cluster = CassandraHosts.getCluster();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // declare variables to store the values submitted via the form
        String moduleCode = request.getParameter("moduleCode");
        String deliverableName = request.getParameter("deliverableName");
        String dueDate = request.getParameter("dueDate");
        String percentageWorth = request.getParameter("percentageWorth");
        String percentageAchieved = request.getParameter("percentageAchieved");
        double perWorth = Double.parseDouble(percentageWorth);
        double perAchieved = Double.parseDouble(percentageAchieved);
        String username = request.getParameter("username");

        //below is an if statement that will check none of the fields have been left empty
        if (moduleCode.equals("") || deliverableName.equals("") || dueDate.equals("") || perWorth == 0.0 || perAchieved == 0.0 || username.equals("")) {
            incompleteError(request, response); // display error message
        }

        // this if statement prevents the user from creating an account that does not have all fields completed
        if (!moduleCode.equals("") && !deliverableName.equals("") && !dueDate.equals("") && perWorth != 0.0 && perAchieved != 0.0 && !username.equals("")){
            ModuleModel del = new ModuleModel();
            del.setCluster(cluster);

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
        response.sendRedirect("/SaveTheSemester");
    }

    private void incompleteError(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String incompleteError = "Not all fields have been completed. Please answer all fields before clicking <b>Add Deliverable</b>";
        RequestDispatcher rd = request.getRequestDispatcher("adddeliverable.jsp");
        request.setAttribute("incompleteError",incompleteError); // display confirmation message that deliverable was added
        rd.forward(request, response);
    }
}
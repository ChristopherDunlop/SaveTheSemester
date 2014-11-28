/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lib.CassandraHosts;
import stores.*;
import models.*;
import java.util.Iterator;

/**
 *
 * @author Christopher
 */
@WebServlet(name = "Progress", urlPatterns = {"/Progress"})
public class Progress extends HttpServlet {

    private Cluster cluster;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }    
    
        public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
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
        
        HttpSession session = request.getSession(); 
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        
        if(lg == null){
            RequestDispatcher rd = request.getRequestDispatcher("/Login");
            rd.forward(request, response); 
        }
        else if(lg.getloggedin()){
            String username = lg.getUsername();
            ModuleModel mm = new ModuleModel();
            mm.setCluster(cluster);
            Set<Module> modules = mm.getStudentModules(username);
            
            if (modules == null){
                RequestDispatcher rd = request.getRequestDispatcher("/Progress.jsp");
                rd.forward(request, response);
            }
            else {
                request.setAttribute("modules", modules);
                Iterator<Module> miterator = modules.iterator();
                int i = 0;

                while (miterator.hasNext()){
                    Module module = miterator.next();
                    Set<Deliverable> deliverables = mm.getDeliverables(username,module.getModuleCode());
                    if(deliverables != null){
                        Iterator<Deliverable> diterator = deliverables.iterator();
                        double finalGradePercentage = 0;
                        double percentageWorth = 0;

                        while (diterator.hasNext()){            
                            Deliverable deliverable = diterator.next();
                            finalGradePercentage = finalGradePercentage + deliverable.getFinalGradePercentage();
                            percentageWorth = percentageWorth + deliverable.getPercentageWorth();
                        }

                        String percent = String.valueOf(Math.round(finalGradePercentage))+ "/" + String.valueOf(Math.round(percentageWorth));
                        request.setAttribute(String.valueOf(i), percent);
                        i++;
                    }
                }
            }
            
            RequestDispatcher rd = request.getRequestDispatcher("/Progress.jsp");
            rd.forward(request, response);
        
        }else{            
            RequestDispatcher rd = request.getRequestDispatcher("/Login");
            rd.forward(request, response);        
        }
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

package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.Convertors;
import models.StudentModel;
import com.datastax.driver.core.Cluster;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.ServletConfig;
import lib.CassandraHosts;
import models.ModuleModel;
import stores.Module;
import stores.Student;

/**
 *
 * @author Shaun Smith
 */
@WebServlet(urlPatterns = {"/Profile/*"})
public class studentProfile extends HttpServlet {
    private Cluster cluster; 
    
     public void init(ServletConfig config) throws ServletException 
    {
        // TODO Auto-generated method stub
        cluster = CassandraHosts.getCluster();
    }
    
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
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
        
        String args[] = Convertors.SplitRequestPath(request);
        String user = args[2];
        
        System.out.println(user);
        getStudentProfile(user, request, response);
    }
    
     private void getStudentProfile(String user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
       StudentModel studMod = new StudentModel();
       studMod.setCluster(cluster);
       Student student = studMod.getStudentInfo(user);
       RequestDispatcher rd = request.getRequestDispatcher("/studentProf.jsp");
       request.setAttribute ("StudentProfile", student);
       getModuleNames(user, request, response);
    }
     
     private void getModuleNames(String user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          ModuleModel moduleMod = new ModuleModel();
          moduleMod.setCluster(cluster);
          Set<Module> module = moduleMod.getStudentModules(user);
          RequestDispatcher rd = request.getRequestDispatcher("/studentProf.jsp");          
          request.setAttribute("modules", module);
          rd.forward(request, response);
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

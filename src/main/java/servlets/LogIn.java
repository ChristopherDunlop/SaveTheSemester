/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lib.CassandraHosts;
import models.StudentModel;
import stores.LoggedIn;


/**
 *
 * @author Luke
 */

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class LogIn extends HttpServlet {

        Cluster cluster=null;
        
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override    
    public void init(ServletConfig config) throws ServletException {
    // TODO Auto-generated method stub
    cluster = CassandraHosts.getCluster();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}

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
        response.sendRedirect("Login.jsp");
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
        //processRequest(request, response);
        
         String username=request.getParameter("username");
        String password=request.getParameter("password");
        
		            if (username.equals(""))
		              {
		        	            error("You must enter a username",response);
		        	            return;
		        	        }
		        	        else if (password.equals(""))
		        	        {
		        	            error("You must enter a password", response);
		        	            return;
		        	        }
		            
        
        StudentModel us = new StudentModel();
        us.setCluster(cluster);
        boolean isValid=us.IsValidStudent(username, password);
        HttpSession session=request.getSession();
        System.out.println("Session in servlet "+session);
        if (isValid){
            LoggedIn lg= new LoggedIn();
            lg.setLoggedin();
            lg.setUsername(username);
            
            session.setAttribute("LoggedIn", lg);
            System.out.println("Session in servlet "+session);
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
	    rd.forward(request,response);
            
        }else{
            response.sendRedirect("/SaveTheSemester/Login.jsp");
        }
    }
    
    
    private void error(String string, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
    	        PrintWriter out = null;
    	        out = new PrintWriter(response.getOutputStream());
    	        System.out.println("Incorrect username or password, please try again...");
    	        //out.println("<h2>" + string + "</h2>");
    	        out.close();
    	        return;	
	}

    /**
     * 
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

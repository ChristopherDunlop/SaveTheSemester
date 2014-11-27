/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.CassandraHosts;
import models.ModuleModel;

import stores.ModuleFile;

/**
 *
 * @author Luke
 */
@WebServlet(name = "addFiles", urlPatterns = {"/addFiles"})
public class addFiles extends HttpServlet {
    private static final long serialVersionUID = 1L;
	Cluster cluster=null;
        public void init(ServletConfig config) throws ServletException {
        cluster = CassandraHosts.getCluster();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileName=request.getParameter("fileName");
        String fileType=request.getParameter("fileType");
        String numPages=request.getParameter("numPages");
        String username=request.getParameter("username");
        
         if (fileName.equals(""))
        {
        	error("Please enter a a File Name ", response);
        	return;
        }
        else if (fileType.equals(""))
        {
        	error("Please enter the File Type ", response);
            return;
        }
        else if (numPages.equals(""))
        {
        	error("Please enter the number of pages in the file ", response);
            return;
        }
         
        ModuleModel fi = new ModuleModel();
        fi.setCluster(cluster);
        fi.addFile(fileName, fileType, numPages, username);
        boolean existingFile = fi.existingFile(fileID);
        
        if (existingFile == true)
        {
        	fi.addFile(fileName, fileType, numPages, username);
        	response.sendRedirect("/SaveTheSemester");
        }
        else
        {
        	response.sendRedirect("/SaveTheSemester");
        } 
    }
    
     private void error(String fault, HttpServletResponse response) throws ServletException, IOException
    {
    	 PrintWriter out = null;
    	 out = new PrintWriter(response.getOutputStream());
    	 out.println("<h1>You have made a mistake, please try again</h1>");
    	 out.close();
    	 return;
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

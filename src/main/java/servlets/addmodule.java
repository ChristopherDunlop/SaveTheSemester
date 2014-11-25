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
import lib.CassandraHosts;
import models.ModuleModel;

/**
 *
 * @author peterbennington
 */
@WebServlet(name = "addmodule", urlPatterns = {"/addmodule"})
public class addmodule extends HttpServlet {

    Cluster cluster = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // declare variables to store the values submitted via the form
        String moduleCode = request.getParameter("moduleCode");
        String moduleName = request.getParameter("moduleName");
        String startDate = request.getParameter("startDate");
        String examDate = request.getParameter("examDate");

        //below is an if statement that will check none of the fields have been left empty
        if (moduleCode.equals("") || moduleName.equals("") || startDate.equals("") || examDate.equals("")) {
            incompleteError(request, response); // display error message
        }

        // this if statement prevents the user from creating an account that does not have all fields completed
        if (!moduleCode.equals("") && !moduleName.equals("") && !startDate.equals("") && !examDate.equals("")) {
            ModuleModel mod = new ModuleModel();
            mod.setCluster(cluster);

            if (mod.addModule(moduleCode, moduleName, startDate, examDate)) {
                RequestDispatcher rd = request.getRequestDispatcher("addmodule.jsp");
                request.setAttribute("moduleAdded", moduleName); // display confirmation message that module was added
                rd.forward(request, response);
            } else {

                RequestDispatcher rd = request.getRequestDispatcher("addmodule.jsp");
                request.setAttribute("moduleExists", moduleName); // display error message that the module already exists, therefore not added
                rd.forward(request, response);
            }
        }
        response.sendRedirect("/SaveTheSemester");
    }

    private void incompleteError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         PrintWriter out = null;
    	 out = new PrintWriter(response.getOutputStream());
    	 out.println("<h1>You have made a mistake, please try again</h1>");
    	 out.close();
    }
}

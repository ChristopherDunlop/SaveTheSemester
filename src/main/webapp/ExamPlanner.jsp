<%-- 
    Document   : ExamPlanner
    Created on : 25-Nov-2014, 15:28:52
    Author     : Tom
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="stores.Module"%>
<%@page import="stores.LoggedIn"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="Styles.css" type="text/css" rel="stylesheet">
        <title>Exam Planner</title>
    </head>
    <body>
        <div class="nav">
            <div class="container">
    <%
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        if (lg != null){
            if(lg. getloggedin())
            {
    %>
                <ul class="nav nav-pills pull-left">
                    <li><a href="/SaveTheSemester">Home</a></li>
                    <li><a href="/SaveTheSemester/Profile/<%=lg.getUsername()%>">Student Profile</a></li>
                    <li><a href="/SaveTheSemester/ExamPlanner/<%=lg.getUsername()%>">Exam Planner</a></li>
                    <li><a href="/SaveTheSemester/Progress">View your Progress</a></li>
                    <li><a href="/SaveTheSemester/AddModule">Add Module</a></li>
                    <li><a href="/SaveTheSemester/AddDeliverable">Add Deliverable</a></li>
                    <li><a href="/SaveTheSemester/addFiles.jsp">Add Files</a></li>
                </ul>
                    
                <ul class="nav nav-pills pull-right">
                    <li><a href="/SaveTheSemester/logout">Logout</a></li>
                </ul>
    <%
                       }
                        }
        else {
    %>
                <ul class="nav nav-pills pull-left">
                    <li><a href="/SaveTheSemester">Home</a></li>
                </ul>
                
                <ul class="nav nav-pills pull-right">
                    <li><a href="/SaveTheSemester/Login">Login</a></li>
                    <li><a href="/SaveTheSemester/Register">Register</a></li>
                </ul>
    <%
             }
    %>
                </ul>
            </div>
        </div>
        
        
        <% Set<Module> modules = (Set<Module>) request.getAttribute("modules"); %>
        
        <%
            if (modules == null){
        %>      <p>No modules found</p>
        <%
            }
            else if (modules.isEmpty()){
        %>      <p>No modules found</p>
        <%
            }
            else {
        %>
            <div class="container">
                <h1>Exam Planner</h1>
                <div class="table-responsive">
                <table class="table table-hover">
            <tr>
                <th>Module Code</th>
                <th>Module Name</th>
                <th>Exam Date</th>
                <th>Days until exam</th>
                <th>Num Files</th>
                <th>Est Files/Day</th>
                <th>Num File Pages</th>
                <th>Est Pages/Day</th>
            </tr>
            
        <%
            Iterator<Module> iterator = modules.iterator();

            while (iterator.hasNext()){
                Module module = iterator.next();
                
                Calendar today = new GregorianCalendar();
                Calendar exam = new GregorianCalendar();
                exam.setTime(module.getExamDate());
                
                SimpleDateFormat formatter = new SimpleDateFormat("EEE dd-MMM-yyyy");
                String examDate = formatter.format(exam.getTime());
                
                final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
                float diffInDays = (exam.getTimeInMillis() - today.getTimeInMillis()) / DAY_IN_MILLIS;
                
                int numOfFiles = module.getNumIncompleteFiles();
                float numFilesPerDay = (float) numOfFiles / diffInDays;
                
                int numOfFilePages = module.getNumIncompletePages();
                float numFilePagesPerDay = (float) numOfFilePages / diffInDays;
        %>
                <tr>
                    <td><%=module.getModuleCode()%></td>
                    <td><%=module.getModuleName()%></td>
                    <td><%=examDate%></td>
                    <td><%=(int) diffInDays%></td>
                    <td><%=numOfFiles%></td>
                    <td><%=String.format("%.2f", numFilesPerDay)%></td>
                    <td><%=numOfFilePages%></td>
                    <td><%=String.format("%.2f", numFilePagesPerDay)%></td>
                    <td><a href="/SaveTheSemester/ModuleFiles/<%=module.getUsername()%>/<%=module.getModuleCode()%>">View files</a></td>
                </tr>
        <%
            }
        %>
            </table>
            </div>
            </div>
        <% } %>
        
        <div class="footer">
            <div class="container">
                <p>&COPY; Study Saviours 2014</p>
            </div>
        </div>
    </body>
</html>

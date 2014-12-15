<%-- 
    Document   : Progress
    Created on : 27-Nov-2014, 20:22:30
    Author     : Christopher
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
        <title>Progress</title>
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
                </ul>
                    
                <ul class="nav nav-pills pull-right">
                    <li><a href="/SaveTheSemester/AddModule">Add Module</a></li>
                    <li><a href="/SaveTheSemester/addFiles.jsp">Add Module File</a></li>
                    <li><a href="/SaveTheSemester/AddDeliverable">Add Deliverable</a></li>
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
        
        <h1>Progress</h1>
        <% Set<Module> modules = (Set<Module>) request.getAttribute("modules"); 

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
                <h1>Progress</h1>
                <div class="table-responsive">
                <table class="table table-hover">
            <tr>
                <th>Module Code</th>
                <th>Module Name</th>
                <th>Week</th>                
                <th>Days until exam</th>
                <th>Percentage Achieved %</th>
            </tr>
            
        <%
            Iterator<Module> iterator = modules.iterator();
            int i = 0;
            while (iterator.hasNext()){
                Module module = iterator.next();
                
                Calendar today = new GregorianCalendar();
                Calendar exam = new GregorianCalendar();
                Calendar start = new GregorianCalendar();
                exam.setTime(module.getExamDate());
                start.setTime(module.getStartDate());
                
                SimpleDateFormat formatter = new SimpleDateFormat("EEE dd-MMM-yyyy");
                String examDate = formatter.format(exam.getTime());
                String startDate = formatter.format(start.getTime());
                               
                final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
                float totalDays = (int) ((exam.getTimeInMillis() - start.getTimeInMillis()) / DAY_IN_MILLIS);
                float diffInDays = (int) ((exam.getTimeInMillis() - today.getTimeInMillis()) / DAY_IN_MILLIS);
        %>
                <tr>
                    <td><%=module.getModuleCode()%></td>
                    <td><a href="/SaveTheSemester/ExamPlanner/<%=lg.getUsername()%>"><%=module.getModuleName()%></a></td>
                    <td><%=Math.round((totalDays-diffInDays)/7)%>/<%=Math.round(totalDays/7)%></td>
                    <td><%=diffInDays%></td>
                    <% if(request.getAttribute(String.valueOf(i)) != null){%>
                    <td><%=request.getAttribute(String.valueOf(i))%></td>
                    <%}else{%>
                    <td>No Deliverables</td>
                    <%}%>
                </tr>
        <%
            i++;
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

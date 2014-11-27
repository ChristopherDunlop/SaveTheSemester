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
        <    <title>Progress</title>
    </head>
    <body>
        <h1>Exam Planner</h1>
        <% Set<Module> modules = (Set<Module>) request.getAttribute("modules"); 
           LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        %>
        
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
            <table border="1">
            <tr>
                <th>Module Code</th>
                <th>Module Name</th>
                <th>Week</th>                
                <th>Days until exam</th>
                <th>Percentage Achieved</th>
            </tr>
            
        <%
            Iterator<Module> iterator = modules.iterator();

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
                    <td><%=(totalDays-diffInDays)/7%>"/"<%=totalDays/7%></td>
                    <td><%=diffInDays%></td>
                    <td>"Percentage to be implemented"</td>
                </tr>
        <%
            }
        %>
            </table>
        <% } %>
    </body>
</html>

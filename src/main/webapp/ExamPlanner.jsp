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
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Planner</title>
    </head>
    <body>
        <h1>Exam Planner</h1>
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
            <table border="1">
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
        <% } %>
    </body>
</html>

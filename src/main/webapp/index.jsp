<%-- 
    Document   : index
    Created on : 24-Nov-2014, 23:12:12
    Author     : Christopher
--%>

<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SaveTheSemester</title>
    </head>
    
    <body>
    <%
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        if (lg != null){
            if(lg. getloggedin())
            {
    %>
    
        <h1><a href="/SaveTheSemester/Login">Login</a></h1>
        <h1><a href="Register.jsp">Register</a></h1>
        <h1><a href="/SaveTheSemester/Profile/<%=lg.getUsername()%>">Student Profile</a></h1>
        <h1><a href="addmodule.jsp">Add Module</a></h1>
        <h1><a href="/addFiles.jsp">Add Files</a></h1>
        <h1><a href="/SaveTheSemester/ExamPlanner/<%=lg.getUsername()%>">Exam Planner</a></h1>
    
    <%
                       }
                        }
        else {
    %>
                <h1><a href="/SaveTheSemester/Login">Login</a></h1>
                <h1><a href="Register.jsp">Register</a></h1>
    
    <%
             }
    %>
    </body>
</html>

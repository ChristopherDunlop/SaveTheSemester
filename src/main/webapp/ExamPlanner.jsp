<%-- 
    Document   : ExamPlanner
    Created on : 25-Nov-2014, 15:28:52
    Author     : Tom
--%>

<%@page import="stores.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Planner</title>
    </head>
    <body>
        <h1>Exam Planner</h1>
        <% Student student = (Student) request.getAttribute("Student"); %>
        <h1><%=student.getFirstName()%> <%=student.getLastName()%></h1>
        
    </body>
</html>

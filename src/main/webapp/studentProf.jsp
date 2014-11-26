<%-- 
    Document   : studentProf
    Created on : 25-Nov-2014, 17:27:05
    Author     : Shaun Smith
--%>

<%@page import="java.util.Set"%>
<%@page import="stores.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Profile</title>
    </head>
    <body>
        <h1>Study Planner Profile:</h1>
        
       <% 
            java.util.LinkedList<Student> studentInfo = (java.util.LinkedList<Student>) request.getAttribute("studentInfo");
       
             if (studentInfo == null)
             {
        %>
        <p>No Profile Found</p>
        <li class="footer"><a href="/savethesemester">Home</a></li>
        <%   }
        
             else
             {
                 Student student = (Student)studentInfo.get(0);
                 String username = student.getUsername();
                 String firstname = student.getFirstName();
                 String lastname = student.getLastName();
                 Set<String> modules = student.getModules();
        %>
        
        <h1>Profile of: <%=username%></h1>
        <p>First Name: <%=firstname%></p>
        <p>Last Name: <%=lastname%></p>
        <p>Modules: <%=modules%></p>
        
        <%  }
        %>
    </body>
</html>

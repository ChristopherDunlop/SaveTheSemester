<%-- 
    Document   : studentProf
    Created on : 25-Nov-2014, 17:27:05
    Author     : Shaun Smith
--%>

<%@page import="stores.Module"%>
<%@page import="models.ModuleModel"%>
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
             Student studentInfo = (Student) request.getAttribute("studentInfo");
             Module moduleName = (Module) request.getAttribute("moduleName");
             if (studentInfo == null)
             {
        %>
        <p>No Profile Found</p>
        <a href="/savethesemester.index.jsp">Home</a>
        <%   }
        
             else
             {
                 Student student = (Student)studentInfo;
                 Module module = (Module)moduleName;
                 String username = student.getUsername();
                 String firstname = student.getFirstName();
                 String lastname = student.getLastName();
                 String moduleNames = module.getModulename();
                 Set<String> modules = student.getModules();
               
                 
                 
        %>
        
        <h1>Profile of: <%=username%></h1>
        <p>First Name: <%=firstname%></p>
        <p>Last Name: <%=lastname%></p>
        <p>Modules: <%=modules%><%=moduleNames%></p>
        
        <%  }
        %>
        <a href="/savethesemester.index.jsp">Home</a>
    </body>
</html>

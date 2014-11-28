<%-- 
    Document   : editProf
    Created on : 28-Nov-2014, 00:42:40
    Author     : Shaun Smith
--%>

<%@page import="stores.Student"%>
<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SavetheSemester - Edit Profile Information</title>
    </head>
    <body>
      <%
          LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
          Student student = (Student)request.getAttribute("studentInfo");
          if(student == null)
          {
      %>
      <p> No Profile to edit! </p>
      <% 
          }
      else
          {
      %>
          <form method="POST"  action="editProfile">
                <ul>
                    <li>User Name <input type="text" name="username" value="<%=student.getUsername()%>" readonly></li>
                    <li>First Name <input type="text" name="firstname" value="<%=student.getFirstName()%>"></li>
                    <li>Last Name <input type="text" name="lastname" value="<%=student.getLastName()%>"></li>
                </ul>
                <br/>
                <input type="submit" value="edit"> 
            </form>
        
        <%
          }
        %>
        
        
        
        
        
        
        
    </body>
</html>

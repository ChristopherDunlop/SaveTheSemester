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
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="Styles.css" type="text/css" rel="stylesheet">
        <title>SavetheSemester - Edit Profile Information</title>
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
        
      <%
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
                    <li>University <input type="text" name="uni" value="<%=student.getUni()%>"></li>
                    <li>Course <input type="text" name="course" value="<%=student.getCourse()%>"></li>
                    <li>Bio <input type="text" name="bio" value="<%=student.getBio()%>"></li>
                </ul>
                <br/>
                <input type="submit" value="edit"> 
            </form>
        
        <%
          }
        %>
    </body>
</html>

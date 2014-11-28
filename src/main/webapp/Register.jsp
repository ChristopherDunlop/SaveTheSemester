<%-- 
    Document   : Register
    Created on : 25-Nov-2014, 16:09:06
    Author     : Luke
--%>
<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="Styles.css" type="text/css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SaveTheSemester: Register</title>
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
        
        <div class="container">
            <form class="form-signin" role="form" method="POST" action="Register">
                <h2 class="form-signin-heading">Register</h2>
                <label for="username" class="sr-only">Username</label>
                <input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" name="password" class="form-control" placeholder="Password" required>
                <br>
                <label for="name" class="sr-only">First Name</label>
                <input type="text" name="name" class="form-control" placeholder="First Name" required>
                <label for="surname" class="sr-only">Second Name</label>
                <input type="text" name="surname" class="form-control" placeholder="Second Name" required>
                <br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>  
            </form>
         </div>
         
    </body>
</html>
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
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="Styles.css" type="text/css" rel="stylesheet">
        <title>SaveTheSemester</title>
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

        <div class="jumbotron">
            <div class="container">
                <h1>Save the Semester</h1>
                <p>It's exam time and you have two weeks to save the semester: <strong>let the games begin!</strong></p>
            </div>
        </div>
                
        <div class="index-body">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <h3>Exam Planner</h3>
                        <p>
                            Keep your revision on track by:<br>
                            <ul>
                                <li>Entering the modules you're studying</li>
                                <li>Entering the documents you need to study</li>
                                <li>Ticking off files as you cover them</li>
                                <li>Tracking your daily study target</li>
                            </ul>
                        </p>
                    </div>
                    
                    <div class="col-md-4">
                        
                    </div>
                    
                    <div class="col-md-4">
                        <h3>Assignment Tracker</h3>
                        <p>
                            Calculate your current semester's progress by:<br>
                            <ul>
                                <li>Entering the % value of assignments and exams</li>
                                <li>Update your assignments with the grades you achieve</li>
                                <li>Check how much of the semester's total grade you have earned so far</li>
                            </ul>
                        </p>
                    </div>
                </div>
            </div>
        </div>
                
        <div class="footer">
            <div class="container">
                <p>&COPY; Study Saviours 2014</p>
            </div>
        </div>
    </body>
</html>

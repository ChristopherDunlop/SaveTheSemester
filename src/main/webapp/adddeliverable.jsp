<%-- 
    Document   : adddeliverable
    Created on : Nov 26, 2014, 11:20:21 AM
    Author     : peterbennington
--%>

<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="Styles.css" type="text/css" rel="stylesheet">
        <title>Add Deliverable</title>
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

        
        <%  // prints a message to screen telling the user they have successfully added that module
            String deliverableAdded = (String) request.getAttribute("deliverableAdded");
            if (deliverableAdded != null) {
                out.println("The deliverable <b>" + deliverableAdded + "</b> has been added.");
            }
            // prints an error message to screen telling the user the module with that module code already exists.
            String deliverableExists = (String) request.getAttribute("deliverableExists");
            if (deliverableExists != null) {
                out.println("The deliverable <b>" + deliverableExists + "</b> already exists.");
            }
            // prints an error message to screen telling the user they have not completed all fields
            String incompleteError = (String) request.getAttribute("incompleteError");
                if (incompleteError != null) {
                    out.println(incompleteError);
                }     
        %>
        
        <% if (lg != null && lg.getloggedin()) {%>
        <div class="container">
                <form class="form-signin" role="form" method="POST" action="AddDeliverable">
                <h2 class="form-signin-heading">Add Deliverable</h2>
                
                <label for="moduleCode">Module Code</label><br>  
                <input type="text" name="moduleCode" maxlength="7" class="form-control" placeholder="Module Code" required>                 
                
                <label for="deliverableName">Deliverable Name</label> 
                <input type="text" name="deliverableName" class="form-control" placeholder="Deliverable Name" required>                  
                 
                <label for="dueDate" >Due Date</label>
                <input type="date"  min="2014-09-01" name="dueDate" class="form-control" placeholder="" required>     
                    
                <label for="percentageWorth" >Percentage Worth</label>
                <input type="number" step="any" min="0" max="100" name="percentageWorth" class="form-control" placeholder="00.00" required>        
                
                <label for="percentageAchieved" >Percentage Achieved</label>
                <input type="number" step="any" min="0" max="100" name="percentageAcheived" class="form-control" placeholder="00.00" required> 
           
                <br/>
                <input type="hidden" value="<%=lg.getUsername()%>" name="username">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Add Deliverable</button>
        </form>
        <%}%>
        
        <div class="footer">
            <div class="container">
                <p>&COPY; Study Saviours 2014</p>
            </div>
        </div>
    </body>
</html>
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
        <title>Add Deliverable</title>
    </head>
    <body>
        <h1>Add Deliverable</h1>
        
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
        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");        
        %>
        
        <% if (lg != null && lg.getloggedin()) {%>
        <form method="POST"  action="adddeliverable">
                <ul>
                    <li>Module Code: <input type="text" name="moduleCode"></li>
                    <li>Deliverable Name: <input type="text" name="deliverableName"></li>
                    <li>Due Date: <input type="date" min="2014-09-01" name="dueDate"></li>
                    <li>Percentage Worth: <input type="text" name="percentageWorth" ></li>
                    <li>Percentage Achieved: <input type="text" name="percentageAchieved"></li>
                </ul>
                <br/>
                <input type="hidden" value="<%=lg.getUsername()%>" name="username">
                <input type="submit" value="Add Deliverable"> 
        </form>
        <%}%>
    </body>
</html>
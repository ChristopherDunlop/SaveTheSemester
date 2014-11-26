<%-- 
    Document   : addmodule
    Created on : Nov 25, 2014, 4:30:12 PM
    Author     : peterbennington
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Module</title>
    </head>
    <body>
        <h1>Add Module</h1>
        
        <%  // prints a message to screen telling the user they have successfully added that module
            String moduleAdded = (String) request.getAttribute("moduleAdded");
            if (moduleAdded != null) {
                out.println("The module <b>" + moduleAdded + "</b> has been added.");
            }
            // prints an error message to screen telling the user the module with that module code already exists.
            String moduleExists = (String) request.getAttribute("moduleExists");
            if (moduleExists != null) {
                out.println("The module <b>" + moduleExists + "</b> already exists.");
            }
            // prints an error message to screen telling the user they have not completed all fields
            String incompleteError = (String) request.getAttribute("incompleteError");
                if (incompleteError != null) {
                    out.println(incompleteError);
                }
        %>
        
        <form method="POST"  action="addmodule">
                <ul>
                    <li>Module Code: <input type="text" name="moduleCode"></li>
                    <li>Module Name: <input type="text" name="moduleName"></li>
                    <li>Start Date: <input type="date" min="2014-09-01" name="startDate"></li>
                    <li>Exam Date: <input type="date" min="2014-12-01" name="examDate"></li>
                </ul>
                <br/>
                <input type="submit" value="Add Module"> 
        </form>
    </body>
</html>

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
            // prints a message to screen telling the user they have successfully added that module
            String moduleExists = (String) request.getAttribute("moduleExists");
            if (moduleExists != null) {
                out.println("The module <b>" + moduleExists + "</b> already exists.");
            }
        %>
        
        <form method="POST"  action="addmodule">
                <ul>
                    <li>Module Code: <input type="text" name="moduleCode" required></li>
                    <li>Module Name: <input type="text" name="moduleName" required></li>
                    <li>Start Date: <input type="date" min="2014-09-01" name="startDate" required></li>
                    <li>Exam Date: <input type="date" min="2014-12-01" name="examDate"required></li>
                </ul>
                <br/>
                <input type="submit" value="Add Module"> 
        </form>
    </body>
</html>

<%@page import="stores.LoggedIn"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="Styles.css" rel="stylesheet">
        <title>Add Module</title>
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
            String moduleAdded = (String) request.getAttribute("moduleAdded");
            if (moduleAdded != null) {
                %>
                <div class="alert alert-success" role="alert" style="text-align: center">
                The module <b><%=moduleAdded %></b> was added successfully.
                </div>
                <%
            }
            // prints an error message to screen telling the user the module with that module code already exists.
            String moduleExists = (String) request.getAttribute("moduleExists");
            if (moduleExists != null) {
                %>
                <div class="alert alert-danger" role="alert" style="text-align: center">
                <b>Error: </b> The module <b><%=moduleExists %></b> already exists.
                </div>
                <%
            }
            // prints an error message to screen telling the user they have not completed all fields
            String incompleteError = (String) request.getAttribute("incompleteError");
                if (incompleteError != null) {
                    %>
                <div class="alert alert-danger" role="alert" style="text-align: center">
                <b>Error: </b><%=incompleteError %>
                </div>
                <%
                }       
        %>
        
        <% if (lg != null && lg.getloggedin()) {%>
        
      <div class="container">
      
      <form class="form-signin" role="form" method="POST" action="AddModule" >
                <h2>Add Module</h2>
                
                <label for="moduleCode">Module Code:</label><br>    
                <input type="text" name="moduleCode" class="form-control" maxlength="7" placeholder="Module Code">
            
                <label for="moduleName">Module Name:</label><br>
                    <input type="text" name="moduleName" class="form-control" placeholder="Module Name">
           
                    <label for="startDate">Start Date:</label><br>
                    <input type="date" min="2014-09-01" name="startDate" class="form-control" placeholder="Start Date">
            
                    <label for="examDate">Exam Date:</label><br>
                    <input type="date" min="2014-12-01" name="examDate" class="form-control" placeholder="Exam Date">
            

                  <input type="hidden" value="<%=lg.getUsername()%>" name="username">
                  <br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Add Module</button>
            </form>
                  <%}%> 
        </div>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        
        <div class="footer">
            <div class="container">
                <p>&COPY; Study Saviours 2014</p>
            </div>
        </div>
    </body>
</html>
<%-- 
    Document   : ModuleFiles
    Created on : 27-Nov-2014, 14:40:12
    Author     : Tom
--%>

<%@page import="java.util.Iterator"%>
<%@page import="stores.ModuleFile"%>
<%@page import="stores.LoggedIn"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="Styles.css" type="text/css" rel="stylesheet">
        <title>Module Files</title>
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
            Set<ModuleFile> moduleFiles = (Set<ModuleFile>) request.getAttribute("moduleFiles");
            
            if (moduleFiles == null){
        %>      <p>No module files found</p>
        <%
            }
            else if (moduleFiles.isEmpty()){
        %>      <p>No module files found</p>
        <%
            }
            else {
        %>
            <div class="container">
                <div class="table-responsive">
                <table class="table table-hover">
                <tr>
                    <th>File Name</th>
                    <th>File Type</th>
                    <th>Num of Pages</th>
                    <th>Completed</th>
                    <th>Date Completed</th>
                </tr>
        <%
                Iterator<ModuleFile> iterator = moduleFiles.iterator();

                while (iterator.hasNext()){
                    ModuleFile moduleFile = iterator.next();

                if (moduleFile.isCompleted()){
        %>      
            <tr class="success">
            <td><%=moduleFile.getFileName()%></td>
            <td><%=moduleFile.getFileType()%></td>
            <td><%=moduleFile.getNumPages()%></td>
            <td>Yes</td>
            <td><%=moduleFile.getDateCompleted()%></td>
            <td>
                <form method="POST">
                    <input type="hidden" name="fileID" value="<%=moduleFile.getFileID()%>">
                    <input type="hidden" name="username" value="<%=(String) request.getAttribute("username")%>">
                    <input type="hidden" name="moduleCode" value="<%=(String) request.getAttribute("moduleCode")%>">
                    <input type="hidden" name="completed" value="false">
                    <button type="submit" class="btn btn-danger">Reset</button>
                </form>
            </td>
        <%  }
            else {
        %>  <tr class="danger">
            <td><%=moduleFile.getFileName()%></td>
            <td><%=moduleFile.getFileType()%></td>
            <td><%=moduleFile.getNumPages()%></td>
            <td>No</td>    
            <td>N/A</td>
            <td>
                <form method="POST" action="ModuleFiles">
                    <input type="hidden" name="fileID" value="<%=moduleFile.getFileID()%>">
                    <input type="hidden" name="username" value="<%=(String) request.getAttribute("username")%>">
                    <input type="hidden" name="moduleCode" value="<%=(String) request.getAttribute("moduleCode")%>">
                    <input type="hidden" name="completed" value="true">
                    <button type="submit" class="btn btn-success">Completed</button>
                </form>
            </td>
        <%  }
        %>
            </tr>
        <%
                }
        %>
                </table>
            </div>
            </div>
        <%
            }
        %>
        
        <div class="footer">
            <div class="container">
                <p>&COPY; Study Saviours 2014</p>
            </div>
        </div>
    </body>
</html>

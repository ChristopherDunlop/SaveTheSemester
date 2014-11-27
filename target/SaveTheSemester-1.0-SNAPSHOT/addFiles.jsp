<%-- 
    Document   : addFiles
    Created on : 26-Nov-2014, 15:19:52
    Author     : Luke
--%>

<%@page import="stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SaveTheSemester - Add Files</title>
      <nav>
            <ul>  
                <li><a href="/SaveTheSemester/index.jsp">Home</a></li>
            </ul>
        </nav>
    <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn"); %>
       
            <h3>Add Files (Revision Materials)</h3>
            <form method="GET"  action="addFiles/<%=lg.getUsername()%>">
                <ul>
                    <li>File Name: <input type="text" name="fileName"></li>
                    <li>File Type: <input type="text" name="fileType"></li>
                    <li>Number of Pages: <input type="text" name="numPages"></li>
                    <li>Module Code: <input type ="text" name ="modCode"></li>
                </ul>
             <input type="submit" value="Add File"> 
             </form>
        
          
         
</html>

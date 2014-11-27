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
<<<<<<< HEAD
    <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn"); %>
       
            <h3>Add Files (Revision Materials)</h3>
            <form method="GET"  action="addFiles/<%=lg.getUsername()%>">
                <ul>
                    <li>File Name: <input type="text" name="fileName"></li>
                    <li>File Type: <input type="text" name="fileType"></li>
                    <li>Number of Pages: <input type="text" name="numPages"></li>
                    <li>Module Code: <input type ="text" name ="modCode"></li>
=======
       
            <h3>Add Files (Revision Materials)</h3>
            <form method="POST"  action="addFiles">
                <ul>
                    <li>File Name: <input type="text" name="filename"></li>
                    <li>File Type: <input type="text" name="filetype"></li>
                    <li>Number of Pages: <input type="text" name="numpages"></li>
>>>>>>> aca065ebae1a4fd22cdcb8d76023a8676493c077
                </ul>
             <input type="submit" value="Add File"> 
             </form>
        
          
         
</html>

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
       
            <h3>Add Files (Revision Materials)</h3>
            <form method="POST"  action="addFiles">
                <ul>
                    <li>File Name: <input type="text" name="filename"></li>
                    <li>File Type: <input type="text" name="filetype"></li>
                    <li>Number of Pages: <input type="text" name="numpages"></li>
                </ul>
             <input type="submit" value="Add File"> 
             </form>
        
          
         
</html>

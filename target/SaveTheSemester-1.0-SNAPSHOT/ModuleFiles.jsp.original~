<%-- 
    Document   : ModuleFiles
    Created on : 27-Nov-2014, 14:40:12
    Author     : Tom
--%>

<%@page import="java.util.Iterator"%>
<%@page import="stores.ModuleFile"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Module Files</title>
    </head>
    <body>
        <%
            Set<ModuleFile> moduleFiles = (Set<ModuleFile>) request.getAttribute("moduleFiles");
        %>
        
        <%
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
            <table border="1">
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
        %>
        <tr>
            <td><%=moduleFile.getFileName()%></td>
            <td><%=moduleFile.getFileType()%></td>
            <td><%=moduleFile.getNumPages()%></td>
        <%
            if (moduleFile.isCompleted()){
        %>      
            <td>Yes</td>
            <td><%=moduleFile.getDateCompleted()%></td>
        <%  }
            else {
        %>  <td>No</td>    
            <td>N/A</td>
        <%  }
        %>
            </tr>
        <%
                }
            }
        %>
    </body>
</html>

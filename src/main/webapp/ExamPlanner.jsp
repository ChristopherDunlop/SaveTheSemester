<%-- 
    Document   : ExamPlanner
    Created on : 25-Nov-2014, 15:28:52
    Author     : Tom
--%>

<%@page import="stores.Module"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="stores.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Planner</title>
    </head>
    <body>
        <h1>Exam Planner</h1>
        <% Set<Module> modules = (Set<Module>) request.getAttribute("modules"); %>
        
        <%
            if (modules != null){
        %>
            <table border="1">
            <tr>
        <%
            Iterator<Module> iterator = modules.iterator();

            while (iterator.hasNext()){
                Module module = iterator.next();
                %>
                <td><%=module.getModuleName()%></td>
                <%
            }
        %>
            </tr>
            </table>
        <% }
            else {
        %>      <p>No modules found</p>
        <%
            }
        %>
    </body>
</html>

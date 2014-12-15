<%-- 
    Document   : studentProf
    Created on : 25-Nov-2014, 17:27:05
    Author     : Shaun Smith
--%>
<%@page import="java.util.Date"%>
<%@page import="stores.LoggedIn"%>
<%@page import="java.util.Iterator"%>
<%@page import="stores.Module"%>
<%@page import="models.ModuleModel"%>
<%@page import="java.util.Set"%>
<%@page import="stores.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="Styles.css" type="text/css" rel="stylesheet">
        <title>Student Profile</title>
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
        
        <h1>Study Planner Profile</h1>
        
       <%    Student studentProfile = (Student) request.getAttribute("StudentProfile");
             Set<Module> modules = (Set<Module>) request.getAttribute("modules");
             if (studentProfile == null)
             {
        %>
        <p>No Profile Found</p>
        <%   }
        
             else
             {
                 String username = studentProfile.getUsername();
                 String firstname = studentProfile.getFirstName();
                 String lastname = studentProfile.getLastName();
                 String university = studentProfile.getUni();
                 String course = studentProfile.getCourse();
                 String bio = studentProfile.getBio();
                 Date date = studentProfile.getDate();
        %>
            <h1><%=username%>'s Profile</h1>
            <a href="/SaveTheSemester/editProfile/<%=lg.getUsername()%>">Edit Profile</a>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <p>First Name: <%=firstname%></p>
                        <p>Last Name: <%=lastname%></p>
                        <p>Date joined:<%=date%></p>
        <% 
            if (studentProfile.getUni() == null)
            {
        %>    
        <p>University:            </p>
        <%    
            }
        else{
        %>        
        <p>University: <%=university%></p>
        <% }  
            if(studentProfile.getCourse() == null)
            {
         %>
         <p>Course:     </p>
         <% 
            }
         else{   
         %>
        <p>Course:<%=course%></p>
        <%  
            }    
        %>
        <% 
        if(studentProfile.getBio()== null )
        {
        %>    
        <p>Bio:     </p>
        <%
        }
        else
        {
        %> 
        <p>Bio:<%=bio%></p>
        <%    
        }
        %>
            </div>
            <div class="col-md-4">
        <%
            if (modules == null)
            {
        %>
        <p>No modules found </p>
        <%  }
            else
            {
                Iterator<Module> iterator = modules.iterator();
                String mC= "";
                String mN= "";
            
        %>
            <div class="table-responsive">
                <table class="table table-hover">
            <tr>
            <th>Module Code</th>
            <th>Module Name</th> 
            </tr>
             <%
             while(iterator.hasNext()){
                    Module m = iterator.next();
                     mC = m.getModuleCode();
                     mN = m.getModuleName();
             %>
            <tr>
            <td><%=mC%></td>
            <td><%=mN%></td> 
            </tr>
            
        
            <%
             }
                 }
             }
            %>
             </table>
            </div>
            </div>
                </div>
            </div>
             
            <div class="footer">
                <div class="container">
                    <p>&COPY; Study Saviours 2014</p>
                </div>
            </div>
    </body>
</html>


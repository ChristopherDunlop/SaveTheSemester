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
        <title>Student Profile</title>
    </head>
    <body>
        <h1>Study Planner Profile:</h1>
        
       <% 
           LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");  
           Student studentProfile = (Student) request.getAttribute("StudentProfile");
             Set<Module> modules = (Set<Module>) request.getAttribute("modules");
             if (studentProfile == null)
             {
        %>
        <p>No Profile Found</p>
        <a href="/savethesemester/index.jsp">Home</a>
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
            <h1>Profile of: <%=username%></h1>
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
            <table>
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
        
        <a href="/SaveTheSemester/editProfile/<%=lg.getUsername()%>">Edit Profile</a>
        <a href="/SaveTheSemester/index.jsp">Home</a>
    </body>
</html>


<%-- 
    Document   : Register
    Created on : 25-Nov-2014, 16:09:06
    Author     : Luke
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SaveTheSemester: Register</title>
    </head>
    <body>
        <header>
        <h1>SaveTheSemester</h1>
        </header>
        <nav>
            <ul>  
                <li><a href="/SaveTheSemester/index">Home</a></li>
            </ul>
        </nav>
       
        <article>
            <h3>Register as user</h3>
            <form method="POST"  action="Register">
                <ul>
                    <li>User Name: <input type="text" name="username"></li>
                    <li>Password: <input type="password" name="password"></li>
                    <li>First Name: <input type="text" name="name"></li>
                    <li>Second Name: <input type="text" name="surname"></li>
                </ul>
                </br>    
             <input type="submit" value="Register"> 

        </article>
    </body>
</html>
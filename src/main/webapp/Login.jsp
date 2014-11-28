<%-- 
    Document   : Login
    Created on : 25-Nov-2014, 13:10:02
    Author     : Christopher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        <title>Login</title>
    </head>
    <body>
        <article>
            
            <form class="form-sigin" method="POST"  action="Login">
                <h2 class="form-sigin-heading">Login</h2>
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    <li>Password <input type="password" name="password"></li>
                </ul>
                <br/>
                <input type="submit" value="Login"> 
            </form>

        </article>
    </body>
</html>

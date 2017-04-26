<%-- 
    Document   : loginView
    Created on : Apr 13, 2017, 4:49:27 PM
    Author     : Khun_Phichz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Login</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Login Page</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <form method="POST" action="doLogin">
       <table border="0">
          <tr>
             <td>User Name</td>
             <td><input type="text" name="userName" value= "${user.userName}" /> </td>
          </tr>
          <tr>
             <td>Password</td>
             <td><input type="text" name="password" value= "${user.password}" /> </td>
          </tr>
          <tr>
             <td>Remember me</td>
             <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
          </tr>
          <tr>
             <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="home">Cancel</a>
             </td>
          </tr>
       </table>
    </form>
 
    <p style="color:blue;">User Name: admin, password: admin or jerry/jerry001</p>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>

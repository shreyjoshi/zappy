<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zappy- admin login</title>
</head>
<h1>
<a href="Home.jsp">home</a></h1>
<center>
<body>
${loginstatus}
<br/><br/><br/>
<h1>ADMIN LOGIN</h1>
<form action="AdminLoginController" method="post">
<br/>
user name:<input type="text" name="aid" required/>
<br/><br/>
password:<input type="password" name="apass" required/>
<br/><br/>
<input type="submit" name="login">
<br/><br/><a href="forgetpass.jsp">forget password</a>
</form>
</body>
</center>
</html>
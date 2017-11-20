<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>forget password</title>
</head>
<a href="Home.jsp">back</a>
<center>
<body>
<h1><font color="red">${forgetpasswordstatus}</font></h1>
<form action="ForgetPass" method="post">
<input type="email" name="userid" required/>
<select name="typeofuser">
		<option>CUSTOMER</option>
		<option>ADMIN</option>
</select>
<input type="submit" value="submit" />
</form>
</body>
</center>
</html>
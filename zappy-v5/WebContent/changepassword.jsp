<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function checkpassword()
{
	var newpass=document.getElementById("newpassword");
	var confirmpass=document.getElementById("confirmnewpassword");
	if(newpass!=confirmpass)
		window.alert("confirm password dose not matches with new password");
} 
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>changeEmployeePassword</title>
</head>
<p align="right"><a href="AdminHome.jsp">dashboard</a>&nbsp;<a href="logout">logout</a></p>
<center>
<h1>
${msg}
</h1>
<body>
<form action="./ChangePassword" method="post">
<pre>
		email id:		<input type="text" name="userid" value="<%=session.getAttribute("admin") %>" readonly/><br/><br/>
	old password:			<input type="text" name="oldpassword" required /><br/><br/>
	new password:			<input type="password" id="newpassword" name="newpassword"  required /><br/><br/>
confirm new password:			<input type="password" id="confirmnewpassword" name="confirmnewpassword" onblur="checkpassword()" required /><br/><br/>
<input type="submit" value="change password" />
</pre>
</form>
</body>
</center>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zappy admin home</title>
</head>
${loginstatus}
<body>
<br/><br/>
<h1>welcome <%=session.getAttribute("admin") %></h1>
<br/>
<h2><a href="AdminHome.jsp">dashboard</a><p align="right"><a href="logout.jsp">logout</a>&nbsp;&nbsp;<a href="changepassword.jsp">change password</a></p></h2>
<center>
<font color="red">${changepasswordmsg}</font>
<h1><a href="uploadProduct.jsp">Upload product</a></h1>
<br/>
<h1><a href="ProductViewController">view all product</a></h1>
<br/>
<h1><a href="ViewAllCustomerController">view all Customer</a></h1>
<br/>
<h1><a href="ViewTodayOrderController">view unplaced order </a></h1>
<br/>
<h1><a href="ViewOrderHistoryController">order history </a></h1>
<br/>
</center>
</body>
</html>
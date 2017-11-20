<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All your Customer</title>
</head>
<body>
<a href="AdminHome.jsp">home</a><br/><br/><br/><br/>
<%@ page import="java.util.ArrayList,beans.Customer" %>
<center>
<table border="1">
<tr><th>cid</th><th>Name</th><th>Password</th><th>mobile no</th><th>address</th></tr>
<%
ArrayList<Customer> list=(ArrayList)request.getAttribute("LIST");
if(!list.isEmpty())
{
for(Customer cc:list)
{	
%>

<tr>

<form action="ManipulateCustomerController" method="post">
	<td><input type="text" name="cid" value="<%=cc.getCid() %>" readonly/></td>
   <td><%=cc.getCname() %></td>
   <td><%=cc.getCpass() %></td>
   <td><%=cc.getCmob() %></td>
   <td><%=cc.getCadd() %></td>
  <!-- <td><input type="submit" name="op" value="update" /></td> -->
   <td><input type="submit" name="op"  value="delete" /></td>
</form>

</tr>

<%
}
}
%>
</table>
</center>
</body>
</html>
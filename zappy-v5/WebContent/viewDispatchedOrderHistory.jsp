<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dispatched orders</title>
</head>
<body>
<h2><p align="right"><a href="AdminHome.jsp">dashboard</a>&nbsp;&nbsp;<a href="logout.jsp">logout</a></p></h2><br/><br/>

<%@page import="java.util.ArrayList,beans.Customer" %>
<%
ArrayList<Customer> list=(ArrayList)request.getAttribute("LIST");
if(!list.isEmpty())
{
	%>
	<table border="6">
<tr><th>pid</th><th>Image</th><th>orderid</th><th>Name</th><th>Price</th><th>Weight</th><th>Details</th><th>customer name</th><th>customer moblie</th><th>customer address</th><th>quantity</th><th>totalamount</th><th>status</th><th>order date</th><th>customer email</th></tr>
<%
for(Customer cc:list)
{	
%>
	<tr>
		<td><%=cc.getPid() %></td>
		<td><image src="images/<%=cc.getImage() %>" height="100" width="100"/></td>
		<td><%=cc.getOrderid()%></td>
	 	<td><%=cc.getPname() %></td>
	 	<td><%=cc.getPrice() %></td>
	 	<td><%=cc.getWeight() %></td>
		<td><%=cc.getDesc() %></td>
		<td><%=cc.getCname() %></td>
		<td><%=cc.getCmob() %></td>
		<td><%=cc.getCadd() %></td>
		<td><%=cc.getQuantity() %></td>
		<td><%=cc.getTa() %></td>
		<td><%if(cc.getStatus()==0){ %>
				<font color="red">order not yet dispatched</font>
			<%}else{ %>
				<font color="green">order dispatched</font>
			<%} %>
		</td>
		<td><%=cc.getOrderdate() %></td>
		<td><%=cc.getCid() %></td>
	</tr>
<%
}
}
else{
%>
<h1><font color="red">nothing in cart!!!!</font></h1>
<%
}
%>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All orders of today</title>
</head>
<h2><a href="AdminHome.jsp">home</a></h2>
<center><font color="red">${dispatchstatus}</font></center>
<body>
<%@ page import="java.util.ArrayList,beans.Customer" %>

<%
ArrayList<Customer> list=(ArrayList)request.getAttribute("LIST");
if(!list.isEmpty())
{
	%>
	<table border="3">
<tr><th>pid</th><th>Image</th><th>orderid</th><th>Name</th><th>Price</th><th>Weight</th><th>Details</th><th>customer name</th><th>customer moblie</th><th>customer address</th><th>quantity</th><th>totalamount</th><th>status</th><th>order date</th><th>customer email</th><th>dispatch</th></tr>
<%
for(Customer cc:list)
{	
%>
	<tr>
	<form action="OperateProduct" method="post">
	
		<td><input type="text" name="pid" value="<%=cc.getPid() %>" readonly/></td>
		<td><image src="images/<%=cc.getImage() %>" height="100" width="100"/></td>
		<td><input type="text" name="orderid" value="<%=cc.getOrderid()%>" readonly/></td>
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
		<td><input type="text" name="cid" value="<%=cc.getCid() %>" readonly/></td>
		<td><input type="submit" value="dispatch"/></td>
	</form>
	</tr>
<%
}
}else
{
%>
	<h1><font color="red">no orders today</font></h1>
<%} %>
</body>
</html>
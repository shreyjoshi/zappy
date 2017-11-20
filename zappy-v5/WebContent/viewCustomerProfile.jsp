<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>your profile</title>
</head>
<h2><p align="right"><a href="customerHome.jsp">dashboard</a>&nbsp;&nbsp;<a href="logout.jsp">logout</a></p></h2><br/><br/><br/><br/><br/>

<body>
<%@page import="java.util.ArrayList,beans.Customer" %>
<%	ArrayList<Customer> list=(ArrayList)request.getAttribute("LIST"); 
	if(!list.isEmpty())
	{
		
		for(Customer c:list)
		{
%><center>
<table border=3>
<form action="UpdateCustomerData" method="post">
		<tr>
			<td>
				id:</td><td><input type="text" name="cid" value="<%=c.getCid()%>" readonly/><br/><br/>
			</td>
		</tr>
		<tr>
			<td>
				name:</td><td><input type="text" name="cname" value="<%=c.getCname()%>" /><br/><br/>
			</td>
		</tr>
		<tr>
			<td>
				mobile no:</td><td><input type="text" name="cmob" value="<%=c.getCmob()%>" /><br/><br/>
			</td>
		</tr>
		<tr>
			<td>
				pass:</td><td><input type="text" name="cpass" value="<%=c.getCpass()%>" /><br/><br/>
			</td>
		</tr>
		<tr>
			<td>
				address:</td><td><input type="text" name="cadd" value="<%=c.getCadd()%>" /><br/><br/>
			</td>
		</tr>
		<tr>
			<td>
				</td><td><input type="submit" value="update" /><br/><br/>
			</td>
		</tr>
	</form>
	</table>
	</center>	
<% 
		}
	}else{
%>
<font color="red"><h2>no profile data...</h2></font>
<%} %>
</body>
</html>
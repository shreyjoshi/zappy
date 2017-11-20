<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>checkout here</title>
<style>
	.cartvalue{
	margin-left:1000px;
	}
</style>
</head>

<body>

<%if(session.getAttribute("cid")==null) 
{
	response.sendRedirect("customerLogin.jsp");
}
else{
%>

<h2><a href="customerHome.jsp">home</a></h2>
<br/><br/>

<%
ArrayList<Customer> list2=(ArrayList)request.getAttribute("LIST2");
if(!list2.isEmpty())
{
	for(Customer cc3:list2)
	{
%>
<div class="cartvalue"> 
<font>
<h1>
quantity:<font color="red"><%=cc3.getCquant() %></font><br/>
total cart value:<font color="red"><%=cc3.getSumamount() %></font><br/>
</h1>
</font>
</div>
<%}
	} %>

<center>
<%@ page import="java.util.ArrayList,beans.Customer" %>

<form action="BuyNow" method="post">
<%
ArrayList<Customer> list=(ArrayList)request.getAttribute("LIST");
if(!list.isEmpty())
{
%>
<table border="3">
<!-- <tr><th>pid</th><th>Image</th><th>Name</th><th>Price</th><th>Weight</th><th>Details</th><th>quantity</th><th>totalamount</th><th>your id</th></tr> -->
<%
int i=0;
for(Customer cc:list)
{	
	if(i%4==0){
%>
<tr>
	<td>
	<center>
<input type="hidden" name="pid" value="<%=cc.getPid() %>" /><br/>
<image src="images/<%=cc.getImage() %>" height="200" width="200" alt="<%=cc.getImage()%>"/><br/>
 product name:`		<%=cc.getPname() %><br/>
 price:			<%=cc.getPrice() %><br/>
 weight:		<%=cc.getWeight() %><br/>
 details: 		<%=cc.getDesc() %><br/>
 quantity:		<%=cc.getQuantity() %><br/>
 total amount:	<%=cc.getTa() %><br/>
 email id:<input type="text" name="cid" value="<%=cc.getCid() %>" readonly/>	<br/>  
	 	</center>
	</td>
<%
}
	else{
		%>
		<td>
	<center>
		<input type="hidden" name="pid" value="<%=cc.getPid() %>" /><br/>
		<image src="images/<%=cc.getImage() %>" height="100" width="100" alt="<%=cc.getImage()%>"/><br/>
		product name:`	<%=cc.getPname() %><br/>
		 price:			<%=cc.getPrice() %><br/>
	 	 weight:		<%=cc.getWeight() %><br/>
		 details: 		<%=cc.getDesc() %><br/>
		 quantity:		<%=cc.getQuantity() %><br/>
		 total amount:	<%=cc.getTa() %><br/>
	 	email id:		<input type="text" name="cid" value="<%=cc.getCid() %>" readonly/>	<br/>  
	 	</center>
	</td>	
		<%
	}
	if(i%4==3)
		out.println("</tr>");
	i++;
}
%>
</table>
<br/>
<font color="orange" size="5"> click on buy now to place your order...</font><br/>
<input type="submit" value="buy now" />
</form>
<%
}else{ %>
<h3>nothing in cart </h3>
<%}} %>
</body>
</center>
</html>
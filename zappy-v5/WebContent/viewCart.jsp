<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>your cart</title>
<style type="text/css">
	body{
	.cartinfo{
		height: 60px;
    width: 60px;
	}
	
	}
</style>
</head>
<script type="text/javascript">
function alertfuction(){
	window.confirm("do you want to delete the product");
}
</script>
<body>
<center>
<%String s1=(String)request.getAttribute("productid");
if(s1!=null)
out.println(s1);
%>
<h1> Product Details</h1>
</center>
<body>
<% if(session.getAttribute("cid")!=null)
	{%>

<h2><p align="right"><a href="customerHome.jsp">dashboard</a>&nbsp;&nbsp;<a href="logout.jsp">logout</a></p></h2><br/><br/>
<%}
else{
	%>
	<h2><p align="right"><a href="ProductCustomerView">Zappy-Home</a></h2>
	<%
}
%>
<%@ page import="java.util.ArrayList,beans.Customer" %>
<font color="red"><h3>${productdelete}</h3></font>
<%
ArrayList<Customer> list2=(ArrayList)request.getAttribute("LIST2");
if(!list2.isEmpty())
{
	for(Customer cc3:list2)
	{
%>

<h2>
<p align="right">
quantity:<font color="orange"><%=cc3.getCquant() %></font><br/>
total cart value:<font color="orange"><%=cc3.getSumamount() %></font><br/>
</p>
</h2>

<%}} %>
<center>
<%
ArrayList<Customer> list=(ArrayList)request.getAttribute("LIST");
if(!list.isEmpty())
{
	%>
	<table border="10">
	
<!-- <tr><th>pid</th><th>Image</th><th>Name</th><th>Price</th><th>Weight</th><th>Details</th><th>quantity</th><th>totalamount</th></tr> -->
<%
int i=0,j=1;
for(Customer cc:list)
{	
	if(i%4==0)
	{
%>
	<tr>	
	<form action="ManipulateCart" method="post">
		<td>
		<center>
			<input type="hidden" name="pid" value="<%=cc.getPid() %>" /><br/>
			<a href="ProductInfo?pid=<%=cc.getPid()%>"><image src="images/<%=cc.getImage() %>" height="250" width="250" /></a><br/>
  	 	    <a href="ProductInfo?pid=<%=cc.getPid()%>"><%=cc.getPname() %></a><br/>
			<div class="cartinfo">
			<h2>cart id:<input type="text" name="addtocartid" value="<%=cc.getAddtocartid() %>" style="width:50px;" readonly/></h2>
			<font color="red"><h4><%=cc.getOrderdate() %></h4></font>
		 		<input type="text" name="price" value="<%=cc.getPrice() %>" id="price<%=j%>" style="width:80px;" readonly /><br/>
 	 	<%=cc.getWeight() %> <br/>
  		<strong><%=cc.getDesc() %></strong><br/>
  		<input type="number" name="quantity" value="1" min="1" id="quantity<%=j%>" style="width:80px;" onChange="calcTotal(<%=j%>)"/><br/>
  		<input type="number" name="totalamount" value="<%=cc.getPrice() %>" id="ta<%=j%>" style="width:50px" required readonly/> (Rs) <br/>

			 	<%if(session.getAttribute("cid")==null) 
			  		{
			  	%>
			  	<input type="hidden" name="cid" value="<%=cc.getCid() %>" readonly /><br/>
			  	<%}else{
			  		%>
			  		<input type="text" name="cid" value="<%=cc.getCid() %>" readonly /><br/>
			  		<%
			  	} %>
			  <h2><input type="submit" name="op"  value="delete" onclick="alertfuction()" />&nbsp;<input type="submit" name="op" value="add to cart" id="addtocart" /> </h2>
		  	</div>
		</center>
		</td>
	</form>
<%
	}
	else{
%>
<form action="ManipulateCart" method="post">
		<td>
		<center>
			<input type="hidden" name="pid" value="<%=cc.getPid() %>" /><br/>
			<a href="ProductInfo?pid=<%=cc.getPid()%>"><image src="images/<%=cc.getImage() %>" height="250" width="250" /></a><br/>
  	 	    <a href="ProductInfo?pid=<%=cc.getPid()%>"><%=cc.getPname() %></a><br/>
			<div class="cartinfo">
			<h2>cart id:<input type="text" name="addtocartid" value="<%=cc.getAddtocartid() %>" style="width:50px;" readonly/></h2>
			<font color="red"><h4><%=cc.getOrderdate() %></h4></font>
		 		<input type="text" name="price" value="<%=cc.getPrice() %>" id="price<%=j%>" style="width:80px;" readonly /><br/>
 	 	<%=cc.getWeight() %> <br/>
  		<strong><%=cc.getDesc() %></strong><br/>
  		<input type="number" name="quantity" value="1" min="1" id="quantity<%=j%>" style="width:80px;" onChange="calcTotal(<%=j%>)"/><br/>
  		<input type="number" name="totalamount" value="<%=cc.getPrice() %>" id="ta<%=j%>" style="width:50px" required readonly/> (Rs) <br/>

			 	<%if(session.getAttribute("cid")==null) 
			  		{
			  	%>
			  	<input type="hidden" name="cid" value="<%=cc.getCid() %>" readonly /><br/>
			  	<%}else{
			  		%>
			  		<input type="text" name="cid" value="<%=cc.getCid() %>" readonly /><br/>
			  		<%
			  	} %>
			  <h2><input type="submit" name="op"  value="delete" onclick="alertfuction()" />&nbsp;<input type="submit" name="op" value="add to cart" id="addtocart" /> </h2>
		  	</div>
		</center>
		</td>
	</form>
		
		<%
	}
	if(i%4==3)
		out.println("</tr>");
	i++;
}
%>
</table>
<form action="CheckoutController" method="get">
<h1><input type="submit" value="checkout" /></h1>
</form>
<%
}
else{
%>
<h1><font color="red">nothing in cart!!!!</font></h1>
<%
}
%>
</center>
</body>
</html>
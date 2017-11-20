<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>products list</title>
</head>
<body>
<center>
<%String s1=(String)request.getAttribute("productid");
if(s1!=null)
out.println(s1);
%>
<h1> Product Details</h1>
<body>
<a href="AdminHome.jsp">home</a>
<font color="red"><h1>${deletemsg}</h1></font>
<%@ page import="java.util.ArrayList,beans.ProductView" %>
<table border="1">
<!-- <tr><th>pid</<th>Name</th><th>Price</th><th>Weight</th><th>Details</th><th>Image</th></tr> -->
<%
ArrayList<ProductView> list=(ArrayList)request.getAttribute("LIST");
if(!list.isEmpty())
{ int i=0;
for(ProductView cc:list)
{	

if(i%4==0)
	{
%>
<tr>
<td>
<center>
<form action="ManipulateData" method="post">

	<input type="hidden" name="pid" value="<%=cc.getPid() %>" /><br/>
	<image src="images/<%=cc.getImage() %>" height="100" width="100" /><br/>
   <%=cc.getPname() %><br/>
   <%=cc.getPrice() %><br/>
   <%=cc.getWeight() %><br/>
   <%=cc.getDesc() %><br/>
  <input type="submit" name="op" value="update" />&nbsp;&nbsp;
   <input type="submit" name="op"  value="delete" />
</form>
</center>
</td>
<%	}else{ %>
<td>
<center>
<form action="ManipulateData" method="post">

	<input type="hidden" name="pid" value="<%=cc.getPid() %>" /><br/>
	<image src="images/<%=cc.getImage() %>" height="100" width="100" /><br/>
   <%=cc.getPname() %><br/>
   <%=cc.getPrice() %><br/>
   <%=cc.getWeight() %><br/>
   <%=cc.getDesc() %><br/>
  <input type="submit" name="op" value="update" />&nbsp;&nbsp;
   <input type="submit" name="op"  value="delete" />
</form>
</center>
</td>
<%
if(i%4==3){
%>
</tr><br/>
<%}
}
	i++; %>
<%
}
}
else{
%>
<h2>product not found</h2>
<%
}

 %>


</body>
</center>

</html>
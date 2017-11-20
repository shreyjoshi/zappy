<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer-Home</title>
${msg} 
<h1 style="background-color:Tomato">Welcome  <%=session.getAttribute("cid")%> </h1>
</head>
<h2><p align="right"><a href="ViewCustomerProfile">view profile</a>&nbsp;&nbsp;<a href="logout.jsp">logout</a></p></h2>
<center>
<h1><font color="orange">${profileupdated}</font></h1>
<body>
<br/>
<h1>Welcome home </h1>
<br/>
<center><h1><font color="red">${orderstatus}</font></h1></center>
<br/><br/>
<br/>
<h1>
<a href="CheckoutController">checkout</a>&nbsp;<br/><br/>
<a href="Home.jsp">View our products</a>&nbsp;<br/><br/>
<a href="ViewCartController">view and edit your cart</a><br/><br/>
<a href="OrderHistoryController">view your previous orders and there status</a><br/><br/><br/>

</h1>
</body>
</center>
</html>
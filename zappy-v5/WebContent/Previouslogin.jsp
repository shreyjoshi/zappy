<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Customer login</title>
</head>
${isinaddtocart}
${pid}
<center>
<body>
<h1 style="background-color:Tomato">Welcome Customer Login here......</h1>
<br/><br/><br/>
<h2> <a href="Home.jsp" >home</a></h2>
<font color="red"><h1>${loginmsg}</h1></font>
<pre>
<form action="CheckCustomerLogin" method="post">
<%if(request.getAttribute("isinaddtocart")!=null)
{
boolean verify=(Boolean)request.getAttribute("isinaddtocart");
if(verify)
{
int pid=(Integer)request.getAttribute("pid");
int quantity=(Integer)request.getAttribute("quantity");
double ta=(Double)request.getAttribute("totalamount");
%>
<input type="hidden" name="verify" value="<%=verify %>" />
<input type="hidden" name="pid" value="<%=pid %>" />
<input type="hidden" name="quantity" value="<%=quantity%>" />
<input type="hidden" name="ta" value="<%=ta %>" />
<%}
}
%>
email id:	<input type="email" name="cid" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,63}$" required/><br/>
pass: 		<input type="password" name="cpass" required/><br/>
<input type="submit" value="login" />
</form>
</pre>
</body>

<h2><a href="SignUp.jsp">sign up</a><br/><br/>&nbsp;&nbsp;<a href="forgetpass.jsp">forget password</a></h2>
</center>
</html>
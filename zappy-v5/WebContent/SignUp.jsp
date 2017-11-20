<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>sign up</title>

<script>
$(document).ready(function(){
	$("#other").show();
	$("#EmailExists").hide();
	//alert("hello");
	$("#cid").blur(function(){
		var data="cid="+$("#cid").val();
		$.ajax({
			url:'verifyEmail.jsp',
			data:data,
			type:'get',
			success:function(result){
				if(result.match("1")){
					$("#other").hide();
					$("#EmailExists").show();
					alert("email id already exists!!!");					
				}else{
					$("#other").show();
					$("#EmailExists").hide();
				}
			}
		});
	});
	$("#reset").click(function(){
		
					$("#other").show();
					$("#EmailExists").hide();
				
	});
});
</script>
</head>
<body>
<h2><a href="Home.jsp">home</a></h2>
<center>
<font color="green">
<%
String m=(String)request.getAttribute("msg");
if(m!=null)
	out.println(m);
%>
<pre>
</font>
<h2>Enter your Details </h2>

<form action="SignUpController" method="post">
email id: 	<input type="email" id="cid" name="cid" pattern="[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,63}$" required/>
<br/>
<div id="other">
Name:		<input type="text" name="cname" title="Only Alphabets allow" pattern="[a-zA-Z][a-zA-Z ]{4,}" required/>
<br/>
Password :	<input type="text" name="cpass" required/>
<br/>
Mobile no:	<input type="text" name="cmob"  maxlength="10" title="Only numeric allow" pattern="[0-9]{10}" required/>
<br/>
Address:	<textarea name="cadd" required ></textarea>
<br/>
	<input type="submit" value="create Account" />
	</div>
	<div id="EmailExists">
	<button type="reset" value="Reset" id="reset">Reset</button><br/><br/>
	<font size="5" color="red">Email already exists...<a href="customerLogin.jsp">click here </a> to login!! </font>
</div>
</form>
</pre>

</center>
</body>
</html>
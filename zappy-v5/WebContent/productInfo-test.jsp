<html>
<%@page import="dao.CustomerDao,beans.Customer" %>
<%@page import="java.util.ArrayList,beans.ProductView" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>product info</title>
<script type="text/javascript">
function calcTotal(j)
	{
		var q=document.getElementById("quantity"+j).value;
		var p=document.getElementById("price"+j).value;
		var ta=p*q;
		document.getElementById("ta"+j).value=ta;
	}
</script>


<!-- 
<script>
$(document).ready(function(){	
	/* $("#custid").load(function(){
		$.ajax({
			url:'checkOrder.jsp',
			data:"cid="+$("#userid").val()"&pid="+$("#pid").val(),
			alert(data);
			type:'post',
			success:function(result){
				if(result==1)
					{
					$("#reviewbox").show();
					}				
			}
		});
	}); */
	
	var data=$("#pid").val();
	alert("hello =="+data);
	$("#reviews").load("loadReviews.jsp?pid="+$("#pid").val(),function(){	
		alert("hellooo");
			/* pid:"pid="+$("#pid").val(), */
		function(data, status){
	        alert("Data: " + data + "\nStatus: " + status);	
	        $("#reviews").htm(data);
		}
	});
	/* 
	$("#reviews").load("loadReviews.jsp?pid="+$("#pid").val(),
			function(){
		$.ajax({
			url:'checkOrder.jsp',
			data:"cid="+$("#userid").val()"&pid="+$("#pid").val(),
			alert(data);
			type:'post',
			success:function(result){
				if(result==1)
					{
					$("#reviewbox").show();
					}				
			}
		});
	}); */
      });
</script> -->
<style>
.imagebox{
margin-left:-800px;
}
.databox{
margin-left:300px;
margin-top:-500px;
font-size:28px;

}
.reviewbox{
margin-left:1100px;
margin-top:-600px;
background-color:yellow;
 
}
</style>
	</head>

<body>
<br/><br/>
 <div class="container-fluid">
			<div class="row">
			<div class="col-sm-3">
			<a href="ProductCustomerView"><img src="images/logo1.jpg" alt="zappyhome" ></img></a>
			</div>
			
			
			<div class="col-sm-9 my_menu">
			<nav class="navbar navbar-default">
			<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mynavbar">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			</div>
			
			
			<div class="collapse navbar-collapse" id="mynavbar">
			<ul class="nav navbar-nav pull-right">
			<li><a href="Home.jsp">Home</a></li>
			
			<%		
	if(session.getAttribute("cid")==null)
	{
			%>
	
			<li><a href="index.jsp">Admin Login</a></li>
			
			<li  class="active"><a href="customerLogin.jsp">Log In</a></li>
			
			<%	}
			else
			{
		
			%>	
			<li><a href="customerHome.jsp"> your profile</a></li>
			<li><a href="logout.jsp">Log out</a></li>&nbsp;
	 		<% } %>
	 		</ul>
	 		</div>
			</nav>		
			</div>	
			</div>

<%
	ArrayList<ProductView> list=(ArrayList)request.getAttribute("productinfo");
	int pid=0;
	if(!list.isEmpty()){
		for(ProductView cc:list){
%>
<%int j=1;%>
<div class="container">
<form action="AddToCart" method="post">
	
		<div class="imagebox"><image src="images/<%=cc.getImage() %>" height="500" width="500" /><br/></div>
		<div class="databox">
		<%pid=cc.getPid(); %>
		<input type="hidden" name="pid" id="pid" style="width:35px;" value="<%=cc.getPid() %>" /><br/><br/>
  	 	product name:<strong><%=cc.getPname() %></strong><br/><br/>
  	 	price:<strong><input type="text" name="price" value="<%=cc.getPrice() %>" id="price<%=j%>" style="width:45px;" readonly /></strong><br/><br/>
  	 	<%=cc.getWeight() %> <br/><br/>
  		<%=cc.getDesc() %><br/><br/>
  		quantity:<strong><input type="number" name="quantity" value="1" min="1" id="quantity<%=j%>" style="width:35px;" onChange="calcTotal(<%=j%>)" /></strong><br/><br/>
  		total amount:<strong><input type="number" name="totalamount" value="<%=cc.getPrice() %>" id="ta<%=j%>" style="width:45px;" required readonly/></strong><br/><br/>
  	 	<br/>
  	 	<input type="submit" value="add to cart" id="addtocart"/><br/><br/>
		</form>
	</div>
<%			
		}
	}
	%>
</div>
	<%
	if(session.getAttribute("cid")!=null)
	{
	String userid=(String)session.getAttribute("cid");
	CustomerDao cd=new CustomerDao();
	if(cd.checkMyOrder(userid,pid)==1)
			{
%>
<br/><br/>
<font color="green" size="6">${reviewpost}</font>
<div id="reviews">

</div>
<br/><br/><br/><br/>

<div id="uploadreviewbox">
<form action="AddReview" method="post">
<input type="hidden" name="cid" value="<%=userid%>" readonly/>
<input type="text" name="pid" value="<%=pid%>" readonly/>
review: <textarea rows="5" cols="25" name="review"></textarea><br/><br/>
rating:5:<input type="radio" name="rating" value="5"/>
		4:<input type="radio" name="rating" value="4"/>
		3:<input type="radio" name="rating" value="3"/>
		2:<input type="radio" name="rating" value="2"/>
		1:<input type="radio" name="rating" value="1"/><br/><br/>
	<input type="submit" value="post review"/>
</form>
</div>
<%}
	} %>
<br/><br/><br/>	
<div class="reviewbox">
<font color="orange">Customer Reviews</font>
<%ArrayList<Customer> list1=(ArrayList)request.getAttribute("reviewinfo");
if(!list1.isEmpty()){
	out.println("<table>");
	for(Customer c:list1)
	{
%>
		<tr><td><strong><%=c.getCid() %></strong></td><br/></tr>
		<tr><td><strong><%=c.getReview() %></strong></td><br/></tr>
		<tr><td><strong><%=c.getRating() %></strong></td><br/></tr>
		</strong>
	<br/><br/>	
<%
	}
	out.println("</table>");
}else{
	out.println("<h2>no reviews to show....</h2>");
}
%>
</div>
</body>
</center>
</html>
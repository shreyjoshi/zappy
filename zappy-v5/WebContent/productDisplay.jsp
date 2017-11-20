	<%@page import="java.util.ArrayList,beans.ProductView,beans.Customer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zappy-Home </title>
<link rel="shortcut icon" type="images/x-icon" href="images/tablogo.ico" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#custid").change(function(){
		$.ajax({
			url:'fetchCartValue.jsp',
			type:'post',
			success:function(result){
				var obj=JSON.parse(result);
				("#quant").val(g.cquant);
				("#sumamount").val(g.sumamount);
			}
		});
	});
});

function calcTotal(j)
	{
		var q=document.getElementById("quantity"+j).value;
		var p=document.getElementById("price"+j).value;
		var ta=p*q;
		document.getElementById("ta"+j).value=ta;
	}
</script>
 
 <style>
body{
background-color:rgb(240,240,235);
background-position: center;
background-repeat: no-repeat;
 background-size: cover;
    background-attachment: fixed;
}
.cartvalue{
margin-left:0;
}
table { 
  width: 100%; 
  border-collapse: collapse; 
}
/* Zebra striping */
tr:nth-of-type(odd) { 
  background: #eee; 
}
th { 
  background: #333; 
  color: white; 
  font-weight: bold; 
}
td, th { 
  padding: 6px; 
  border: 1px solid #ccc; 
  text-align: left; 
}
@media 
only screen and (max-width: 760px),
(min-device-width: 768px) and (max-device-width: 1024px)  {

	/* Force table to not be like tables anymore */
	table, thead, tbody, th, td, tr { 
		display: block; 
	}
	
	/* Hide table headers (but not display: none;, for accessibility) */
	thead tr { 
		position: absolute;
		top: -9999px;
		left: -9999px;
	}
	
	tr { border: 1px solid #ccc; }
	
	td { 
		/* Behave  like a "row" */
		border: none;
		border-bottom: 1px solid #eee; 
		position: relative;
		padding-left: 50%; 
	}
	
	td:before { 
		/* Now like a table header */
		position: absolute;
		/* Top/left values mimic padding */
		top: 6px;
		left: 6px;
		width: 45%; 
		padding-right: 10px; 
		white-space: nowrap;
	}
	

}

.my_menu{ 
	margin-top:40px;}
.my_menu .navbar-default{
	margin-top:47px;
	background:none; border:none;}
.my_menu .navbar-nav .active a, .my_menu .navbar-nav .active a:hover{background:#006C3A; color:#fff;}
.my_menu .navbar-nav li a:hover{background:#006C3A; color:#fff;}
@media(max-width:1200px){
	.my_menu .pull-right{float:none !important;}
	.cartvalue .pull-left{float:none !important;}
	}
	
	
	
	
	
</style>
<style>  
body { padding-top: 90px; }
#myCarousel .nav a small {
    display:block;
}
#myCarousel .nav {
	background:#eee;
}
#myCarousel .nav a {
    border-radius:0px;
}
</style>
<script>
$(document).ready( function() {
    $('#myCarousel').carousel({
		interval:   4000
	});
	
	var clickEvent = false;
	$('#myCarousel').on('click', '.nav a', function() {
			clickEvent = true;
			$('.nav li').removeClass('active');
			$(this).parent().addClass('active');		
	}).on('slid.bs.carousel', function(e) {
		if(!clickEvent) {
			var count = $('.nav').children().length -1;
			var current = $('.nav li.active');
			current.removeClass('active').next().addClass('active');
			var id = parseInt(current.data('slide-to'));
			if(count == id) {
				$('.nav li').first().addClass('active');	
			}
		}
		clickEvent = false;
	});
});
</script>
</head>

<body>

<br/>
			<div class="container-fluid">
			<div class="row">
			<nav class="navbar navbar-default navbar-fixed-top">
			<div class="col-sm-3">
			<a href="ProductCustomerView"><img src="images/logo1.jpg" alt="zappyhome" ></img></a>
			</div>
			
			
			<div class="col-sm-7 my_menu">
			
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
			<li><a href="logout.jsp">Log out</a></li>
	 		<% } %>
	 		</ul>
	 		</div>
			
			</div>	
			
<br/>			
			<div class="col-sm-2">			

	 <%	
	ArrayList<Customer> list2=(ArrayList)request.getAttribute("LIST2");
	if(!list2.isEmpty())
	{
		for(Customer c:list2)
		{
			int x=c.getCquant();
%>
	<div class="cartvalue">
<!-- 	<a href="customerHome.jsp"><font style="background-color:yellow;" size="5"></font></a> -->

	<div class="alert alert-info">
		<font color="black">
		<strong><%String xx=(String)session.getAttribute("cid"); 
		if(xx!=null)
		{
			%>
			<font color="orange" size="3"><%=session.getAttribute("cid")%></font>
			<%
		}
%></strong>
			 Total products:<font color="red"><%=x%><br/></font>Cart value:<font color="red"><%=c.getSumamount()%>Rs.</font> <br/>
			 	 
			 	 <%if(x>0 && session.getAttribute("cid")!=null)
			 		 { %>
			 		  <a href="ViewCartController"> view cart</a>&nbsp;
			 	  <a href="CheckoutController?cid=<%=session.getAttribute("cid")%>">checkout</a>	 	  
			 	 <% }else
			 		 {
			 		 %>
			 		 <a href="ViewCartIpaddress">cart</a>&nbsp;
			 		<!-- <font color="red">login to checkout</font> -->
			 		<% }%>
		 </font>
		 </div>
		 </div>
		</div>
		</div>		
<%
		}
	}
	else{
		%>
			<font color="red">nothing in cart</font>
		<%
	}	 
%>
	</nav>	
		
			</div>
			</div>
			</div>
	<br/><br/>		
			<div class="container-fluid">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
    
      <!-- Wrapper for slides -->
      <div class="carousel-inner">
      
        <div class="item active">
        <img src="images/pic1.jpg">
           <div class="carousel-caption">
           </div>
        </div><!-- End Item -->
 
         <div class="item">
          <img src="images/pic2.jpg">
           <div class="carousel-caption">
            </div>
        </div><!-- End Item -->
        
        <div class="item">
          <img src="images/pic3.jpg">
           <div class="carousel-caption">
           </div>
        </div><!-- End Item -->
        
        <div class="item">
          <img src="images/pic4.jpg">
           <div class="carousel-caption">
          </div>
        </div><!-- End Item -->
                
      </div><!-- End Carousel Inner -->

	<ul class="nav nav-pills nav-justified">
          <li data-target="#myCarousel" data-slide-to="0" class="active"><a href="#">Go's dairy<small>fresh milk products</small></a></li>
          <li data-target="#myCarousel" data-slide-to="1"><a href="#">Happy Chef<small>Exclusively on zappy</small></a></li>
          <li data-target="#myCarousel" data-slide-to="2"><a href="#">Coming soon<small>Go cashless</small></a></li>
          <li data-target="#myCarousel" data-slide-to="3"><a href="#">President's butter<small>Lorem ipsum dolor sit</small></a></li>
        </ul>
    </div><!-- End Carousel -->
</div>

<center>

<h3>${msgsignup} <font color="green">${cartmsg}${buystatus}</font></h3>
<%
	ArrayList<ProductView> list=(ArrayList)request.getAttribute("LIST");
	if(!list.isEmpty())
	{
%>
<table border=0> 
<!-- <tr><th>pid</<th><th>image</th><th>Pname</th><th>price</th><th>weight</th><th>Details</th><th>quantity</th><th>total amount</th></tr> -->
<%
int i=0,j=1;
	for(ProductView cc:list)
	{
		if(i%4==0)
		{
%>
<tr>
<td>	
<center>
<font size="2">
<form action="AddToCart" method="post">
		<input type="hidden" name="pid" value="<%=cc.getPid() %>" /><br/>
		<a href="ProductInfo?pid=<%=cc.getPid()%>"><image src="images/<%=cc.getImage() %>" height="250" width="250" /></a><br/>
  	 	<a href="ProductInfo?pid=<%=cc.getPid()%>"><%=cc.getPname() %></a><br/>
  	 	<input type="text" name="price" value="<%=cc.getPrice() %>" id="price<%=j%>" style="width:80px;" readonly /><br/>
 	 	<%=cc.getWeight() %> <br/>
  		<strong><%=cc.getDesc() %></strong><br/>
  		<input type="number" name="quantity" value="1" min="1" id="quantity<%=j%>" style="width:80px;" onChange="calcTotal(<%=j%>)" /><br/>
  		<input type="number" name="totalamount" value="<%=cc.getPrice() %>" id="ta<%=j%>" style="width:50px" required readonly/> (Rs) <br/>
  	 	<input type="submit" value="add to cart" id="addtocart" />  	 
</form>
		<%-- <form action="ProductInfo" method="get">
					<input type="hidden" name="pid" value="<%=cc.getPid()%>" />
					<input type="submit" value="view details"/>
			</form> --%>
				</font>	
			</center>
 </td>
<%j++;	}
		else{  
%>	
	<td>	
<center>
<font size="2">
<form action="AddToCart" method="post">
		<input type="hidden" name="pid" value="<%=cc.getPid() %>" /><br/>
		<a href="ProductInfo?pid=<%=cc.getPid()%>"><image src="images/<%=cc.getImage() %>" height="250" width="250" /></a><br/>
  	 	<a href="ProductInfo?pid=<%=cc.getPid()%>"><%=cc.getPname() %></a><br/>
  	 	<input type="text" name="price" value="<%=cc.getPrice() %>" id="price<%=j%>" style="width:80px;" readonly /><br/>
  	 	<%=cc.getWeight() %> <br/>
  		<strong><%=cc.getDesc() %></strong><br/>
  		<input type="number" name="quantity" value="1" min="1" id="quantity<%=j%>" style="width:80px;" onChange="calcTotal(<%=j%>)" /><br/>
  		<input type="number" name="totalamount" value="<%=cc.getPrice() %>" id="ta<%=j%>" style="width:50px;" required readonly/>(Rs)<br/>
  	 	<input type="submit" value="add to cart" id="addtocart"/>	 
</form>
		<%-- <form action="ProductInfo" method="post">
				<input type="hidden" name="pid" value="<%=cc.getPid()%>" />
				<input type="submit" value="view details"/>
		</form> --%>
			</font>
		</center>
	</td>		
<%if(i%4==3){
%></tr><%	
} 
j++;
}
i++;
		}
	}
	else{
%>
<font color="red" size="8">no products to display</font>
<%} %>

</table>
</center>
</body>
</html>
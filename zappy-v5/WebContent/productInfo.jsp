<html>
<%@page import="dao.CustomerDao,beans.Customer" %>
<%@page import="java.util.ArrayList,beans.ProductView" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>product info</title>

<style>
body{
opacity:10;
background:#fff;
background-size: cover; 
}
ul > li{margin-right:25px;font-weight:lighter;cursor:pointer}
li.active{border-bottom:3px solid silver;}

.item-photo{display:flex;justify-content:center;align-items:center;border-right:1px solid #f6f6f6;}
.menu-items{list-style-type:none;font-size:11px;display:inline-flex;margin-bottom:0;margin-top:20px}
.btn-success{width:100%;border-radius:0;}
.section{width:100%;margin-left:-15px;padding:2px;padding-left:15px;padding-right:15px;background:#f8f9f9}
.title-price{margin-top:30px;margin-bottom:0;color:black}
.title-attr{margin-top:0;margin-bottom:0;color:black;}

.btn-minus{cursor:pointer;font-size:7px;display:flex;align-items:center;padding:5px;padding-left:10px;padding-right:10px;border:1px solid black;border-radius:2px;border-right:0;}
.btn-plus{cursor:pointer;font-size:7px;display:flex;align-items:center;padding:5px;padding-left:10px;padding-right:10px;border:1px solid black;border-radius:2px;border-left:0;}


div.section > div {width:100%;display:inline-flex;}
div.section > div > input {margin:0;padding-left:5px;font-size:10px;padding-right:5px;max-width:18%;text-align:center;}
.attr,.attr2{cursor:pointer;margin-right:5px;height:20px;font-size:20px;padding:22px;border:1px solid grey;border-radius:2px;}
.attr.active,.attr2.active{ border:1px solid orange;}

@media (max-width: 426px) {
    .container {margin-top:0px !important;}
    .container > .row{padding:0 !important;}
    .container > .row > .col-xs-12.col-sm-5{
        padding-right:0 ;    
    }
    .container > .row > .col-xs-12.col-sm-9 > div > p{
        padding-left:0 !important;
        padding-right:0 !important;
    }
    .container > .row > .col-xs-12.col-sm-9 > div > ul{
        padding-left:10px !important;
        
    }            
    .section{width:104%;}
    .menu-items{padding-left:0;}
}
.my_menu{ 
	margin-top:27px;}
.my_menu .navbar-default{
	background:none; border:none;}
.my_menu .navbar-nav .active a, .my_menu .navbar-nav .active a:hover{background:#006C3A; color:#fff;}
.my_menu .navbar-nav li a:hover{background:#006C3A; color:#fff;}
@media(max-width:1200px){
	.my_menu .pull-right{float:none !important;}
	.cartvalue .pull-left{float:none !important;}
	}
</style>
</head>


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

<script>
   $(document).ready(function(){
            //-- Click on detail
            $("ul.menu-items > li").on("click",function(){
                $("ul.menu-items > li").removeClass("active");
                $(this).addClass("active");
            })

            $(".attr,.attr2").on("click",function(){
                var clase = $(this).attr("class");

                $("." + clase).removeClass("active");
                $(this).addClass("active");
            })

            //-- Click on QUANTITY
            $(".btn-minus").on("click",function(){
                var now = $(".section > div > input").val();
                if ($.isNumeric(now)){
                    if (parseInt(now) -1 > 0){ now--;}
                    $(".section > div > input").val(now);
                }else{
                    $(".section > div > input").val("1");
                }
            })            
            $(".btn-plus").on("click",function(){
                var now = $(".section > div > input").val();
                if ($.isNumeric(now)){
                    $(".section > div > input").val(parseInt(now)+1);
                }else{
                    $(".section > div > input").val("1");
                }
            })                        
        }) 
</script>

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
	<div class="row">
               <div class="col-xs-4 item-photo">
                    <img style="max-width:100%;" src="images/<%=cc.getImage() %>" height="500px"/>
                </div>
                
        <div class="col-xs-1">
        </div>
                 <div class="col-xs-5" style="border:0px black">
                    <!-- Datos del vendedor y titulo del producto -->
                    <h2><%=cc.getPname() %></h2>    
                    <h3 style="color:#337ab7">likes for <a href="#"><%=cc.getPname() %></a> · <small style="color:#337ab7">(5054 likes)</small></h3>
        
                    <!-- Precios -->
                    <h3 class="title-price"><small>OFFER PRICE</small></h3>
                    <h3 style="margin-top:0px;"><input type="text" name="price" value="<%=cc.getPrice() %>" id="price<%=j%>" style="width:50px;" readonly />Rs</h3>
        
                    <!-- Detalles especificos del producto -->
                    <div class="section" style="margin-top:15px;">
                        <h3 class="title-attr" style="margin-top:5px;" ><small>TASTE</small></h3>                    
                        <div >
                            <strong><div class="attr" style="width:auto;background:#fff;"><%=cc.getDesc() %></div></strong>
                        </div>
                    </div>
                    <br/>
                    <div class="section">
                        <h3 class="title-attr"><small>WEIGHT</small></h3>                    
                        <div>
                            <strong><div class="attr2"><%=cc.getWeight() %></div></strong>
                        </div>
                    </div>   
                    <div class="section" style="padding-bottom:20px;">
                        <h3 class="title-attr"><small>QUANTITY</small></h3>                    
                        <div>
                            <div class="btn-minus"><span class="glyphicon glyphicon-minus" onClick="calcTotal(<%=j%>)"></span></div>
                            <input type="text" name="quantity" value="1" min="1" id="quantity<%=j%>"  onChange="calcTotal(<%=j%>)" />
                            <div class="btn-plus"><span class="glyphicon glyphicon-plus" onClick="calcTotal(<%=j%>)" ></span></div>
                        </div>
                    </div>
                    <div>
                     <h2 class="title-price"><small>TOTAL PRICE</small></h2>
                    <h3 style="margin-top:0px;"><input type="number" name="totalamount" value="<%=cc.getPrice() %>" id="ta<%=j%>" style="width:65px;" required readonly/></h3>
                         </div>           
        
                    <!-- Botones de compra -->
                    <div class="section" style="padding-bottom:20px;">
                        <button type="submit" class="btn btn-success" ><span style="margin-right:20px" class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Add to cart</button>
                        <h6><a href="#"><span class="glyphicon glyphicon-heart-empty" style="cursor:pointer;"></span> Save to review</a></h6>
                        </div>
         </div>
         </div> 
                        </form>
                          
                </div>                              
        
<%			
		}
	}
	%>
<%-- </div>
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
</div> --%>
</body>

</html>
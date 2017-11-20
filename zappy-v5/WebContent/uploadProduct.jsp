<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<title>upload product</title>
</head>
<script>
$(document).ready(function(){
	//alert("hello");
$("#filename").change(function () {
    var fileExtension = ['jpeg', 'jpg', 'png'];
    //alert(fileExtension);
    var x=$("#filename").val().split('.').pop().toLowerCase();
    //alert(x);
    if ($.inArray(x, fileExtension) == -1) {
        alert("Only formats are allowed : "+fileExtension.join(', '));
        $("#filename").val('');
    }
});
});
</script>
<h1><a href="AdminHome.jsp">dashboard</a></h1>
<body>
<center>
<font color="green">${uploadstatus}</font>
<h1>Upload new product </h1>
 
 <table>
 <form action="UploadProduct" method="post" enctype="multipart/form-data">
 <tr><td>Product Name</td>	<td><input type="text" name="pname" required/><br/><br/></td></tr> 
 <tr><td>price</td><td>		<input type="text" name="price" title="Only numeric allow" pattern="^\d+(?:\.\d{2})" placeholder="in rupess" required/><br/><br/></td></tr> 
<tr><td> weight</td><td>	<input type="text" name="weight" id="weight" title="Only numeric allow" pattern="^[0-9]*$" required placeholder="in grams"/><br/><br/></td></tr>	
<tr><td> details</td><td>	<textarea name="details" required></textarea><br/><br/></td></tr>	
 <tr><td>Image</td><td>		<input type="file" name="file" id="filename" accept="jpg" required/> <br/><br/></td></tr><br/>	
   		<tr><td></td><td><input type="submit" name="add product" /></td></tr>
  </form>
 </table>

 
 </center>
</body>
</html>
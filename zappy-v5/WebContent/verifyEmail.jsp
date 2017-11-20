<%@page import="dao.CustomerDao" %>
<%
String cid=request.getParameter("cid");
CustomerDao cd=new CustomerDao();
int x=cd.verifyEmail(cid);
if(x==1){
	out.println("1");
}else{
	out.println("0");
}
%>
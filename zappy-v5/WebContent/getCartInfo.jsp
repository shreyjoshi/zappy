<%@ page import="beans.Customer" %>
<%
Customer c=(Customer)request.getAttribute("cartvalue");
int quant=c.getCquant();
double sumamount=c.getSumamount();
%>
out.println(quant);
out.println(sumamount);
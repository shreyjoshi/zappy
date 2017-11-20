<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@page import="dao.CustomerDao,java.util.TreeMap,com.google.gson.Gson" %>  
     <% 	
		String cid=(String)request.getAttribute("cid");
		CustomerDao cd=new CustomerDao();
		TreeMap<String , String> tm =cd.getCartValue(cid);
		Gson g=new Gson();
		String json=g.toJson(tm);
		System.out.println(json);
		
		out.println(json);
	%>
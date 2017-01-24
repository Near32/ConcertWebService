<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title>Order</title>
		<style>
		body {
		 font-size: 20px;
		 color: black;
		 font-family: Calibri;
		}
		
		td {
		 font-size: 15px;
		 color: black;
		 width: 200px;
		 height: 22px;
		 text-align: center;
		}
		.heading {
		 font-size: 18px;
		 color: white;
		 font: bold;
		 background-color: black;
		 border: thick;
		}
		</style>
	</head>
	
	<body>
		<center>
		
		<h1>Error</h1>
		
		<br/><br/><br/>
		
		<p>
		That order cannot be made : the concert has already occured.
		</p>
		
		<br/><br/><br/>
			
		<a href="<%=request.getContextPath()%>/redirectCustomerPage">Your Customer Page</a> | <a href="<%=request.getContextPath()%>/concerts">View the list of concerts</a> 
		
		</center>
	</body>
	
</html>
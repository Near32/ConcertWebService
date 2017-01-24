<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title>Login</title>
	</head>
	
	<body>
		<center>
		<h3>Login</h3>
		
		<br/>
		
		<p>
		Failure. Please, try again.
		</p>
		
		<form:form method="POST" action="ConcertWebService0.1/checkLogin">
		
		E-mail:<form:input path="email"/> 
		
		<font color="red"><form:errors path="email"/></font><br/><br/>
		
		Password:<form:password path="password"/>
		
		<font color="red"><form:errors path="password"/></font><br/><br/>
		
		<input type="submit" value="Login"/>
		
		</form:form>
		
		</center>
	</body>
</html>	
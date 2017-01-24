<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>Index of The Web Service</title>
	</head>
	
	<body>
		<center>
		
		<div id="welcome">
    		<p>Welcome <c:out value="${customerName}"></c:out> !</p>
   		</div>
   		
		<h2>What do you want to do ?</h2>
		
		<c:if test="${logged}">
			<h3>See your customer page :</h3>
			<form:form method="GET" action="/ConcertWebService0.1/redirectCustomerPage">
				<table>
					<tr>
					<td>
					<input type="submit" value="Redirect to your customer page"/>
					</td>
					</tr>
				</table>
			</form:form>
		</c:if>
		
		<h3>See the list of concerts :</h3>
		<form:form method="GET" action="/ConcertWebService0.1/redirectConcertList">
			<table>
				<tr>
				<td>
				<input type="submit" value="Redirect to concerts list"/>
				</td>
				</tr>
			</table>
		</form:form>
		
		<c:if test="${not logged}">
		
			<h3>Login :</h3>
			<form:form method="GET" action="/ConcertWebService0.1/redirectLogin">
				<table>
					<tr>
					<td>
					<input type="submit" value="Redirect to login page"/>
					</td>
					</tr>
				</table>
			</form:form>
			
			
			<h3>Register yourself :</h3>
			<form:form method="GET" action="/ConcertWebService0.1/redirectRegistration">
				<table>
					<tr>
					<td>
					<input type="submit" value="Redirect to registration page"/>
					</td>
					</tr>
				</table>
			</form:form>
		</c:if>
		
		</center>
	</body>
</html>
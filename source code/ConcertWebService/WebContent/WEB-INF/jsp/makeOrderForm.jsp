<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title>New Order</title>
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
		<h2>Order Information</h2>
		
		<p>
		You are logged as <c:out value="${CustomerSession.firstName}"></c:out> <c:out value="${CustomerSession.lastName}"></c:out>. <a href="<%=request.getContextPath()%>/login">Not you ?</a>
		</p>
		
		<p>
		Concert : <c:out value="${ConcertSession.name}"></c:out>
		</p>
		
		<form:form method="POST" action="/ConcertWebService0.1/addOrder">
		   <table>
		    <tr>
		        <td><form:label path="seatA">Number of tickets of seat rank A</form:label></td>
		        <td><form:input path="seatA" /></td>
		    </tr>
		    
		    <tr>
		        <td><form:label path="seatB">Number of tickets of seat rank B</form:label></td>
		        <td><form:input path="seatB" /></td>
		    </tr>
		    
		    <tr>
		        <td><form:label path="seatC">Number of tickets of seat rank C</form:label></td>
		        <td><form:input path="seatC" /></td>
		    </tr>
		    
		    <tr>
		        <td><form:label path="seatD">Number of tickets of seat rank D</form:label></td>
		        <td><form:input path="seatD" /></td>
		    </tr>
		    
		    <tr>
		        <td><form:label path="seatS">Number of tickets of seat rank S</form:label></td>
		        <td><form:input path="seatS" /></td>
		    </tr>
		    
		    <tr>
		        <td colspan="2">
		            <input type="submit" value="Submit"/>
		        </td>
		    </tr>
		</table>  
		</form:form>
		
		<br/><br/><br/>
		
		<a href="<%=request.getContextPath()%>/redirectCustomerPage">Your Customer Page</a> | <a href="<%=request.getContextPath()%>/concerts">View the list of concerts</a>
		
	</center>
	</body>
</html>
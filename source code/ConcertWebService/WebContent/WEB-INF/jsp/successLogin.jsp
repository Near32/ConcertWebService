<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title>Customer Page</title>
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
		
		<h1>Welcome <c:out value="${customerName}"/></h1><br>
		
		<br/><br/><br/>
		
		<c:if test="${not isThereOrders}">
	   		<div id="welcome">
	    		<p>
	    		You haven't made any orders yet.
	    		</p>
	    		<p>
	    		Please, consider to <a href="concerts">order tickets</a>.
	    		</p>
	   		</div>
		</c:if>
		
		<c:if test="${isThereOrders}">
			<div id="listsOrders">
				
				<h2>Here are your orders :</h2>
				
				<br/>
				<table border="1">
				   <tr>
				    <td class="heading">Concert</td>
				    <td class="heading">Date</td>
				    <td class="heading">Hall</td>
				    <td class="heading">Seat(s) of rank A</td>
				    <td class="heading">Seat(s) of rank B</td>
				    <td class="heading">Seat(s) of rank C</td>
				    <td class="heading">Seat(s) of rank D</td>
				    <td class="heading">Seat(s) of rank S</td>
				    <td class="heading"> Delete ?</td>
				   </tr>
				   <c:forEach var="order" items="${ordersList}">
				    <tr>
				     <td>${order.nameConcert}</td>
				     <td>${order.date}</td>
				     <td>${order.nameHall}</td>
				     <td>${order.seatA}</td>
				     <td>${order.seatB}</td>
				     <td>${order.seatC}</td>
				     <td>${order.seatD}</td>
				     <td>${order.seatS}</td>
				     <td><a href="<%=request.getContextPath()%>/deleteOrder?id=${order.id}">Delete</a></td>
				     </tr>
				   </c:forEach>
	  			</table>
				
			</div>
		</c:if>
		
		<br/><br/><br/>
		
		<h2>Your informations :</h2>
		
		<table>
        <tr>
        	<td>First Name</td>
        	<td>${CustomerSession.firstName}</td>
        </tr>
        <tr>
        	<td>Last Name</td>
        	<td>${CustomerSession.lastName}</td>
    	</tr>
    	
    	<tr>
        	<td>E-mail</td>
        	<td>${CustomerSession.email}</td>
    	</tr>
    
    	<tr>
        	<td>Address</td>
        	<td>${CustomerSession.address}</td>
    	</tr>
    	
    	<tr>
        	<td>Phone Number</td>
        	<td>${CustomerSession.phoneNumber}</td>
    	</tr>
		</table>
		
		<br/><br/><br/>
			
		<a href="<%=request.getContextPath()%>/index">Back</a> | <a href="<%=request.getContextPath()%>/concerts">View the list of concerts</a> 
		
		</center>
	</body>
</html>	
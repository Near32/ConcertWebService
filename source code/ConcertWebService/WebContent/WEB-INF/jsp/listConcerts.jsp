<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>List of Concerts</title>
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
	
	<c:if test="${logged == true}">
   		<div id="welcome">
    		<p>You are logged as <c:out value="${userName}"></c:out> !</p>
   		</div>
	</c:if>
	
	<c:if test="${logged == false}">
   		<div id="welcome">
    		<p>
    		You are not logged in.
    		</p>
    		<p>
    		Please, consider to <a href="/ConcertWebService0.1/redirectRegistration">register</a> or <a href="/ConcertWebService0.1/login">login</a>, before ordering tickets.
    		</p>
   		</div>
	</c:if>
	
	  <br /> <br /> <br /> 
	  <b>Concerts List </b>
	  <br /> <br />
	  	
	  <table border="1">
	   <tr>
	    <td class="heading">Name</td>
	    <td class="heading">Players</td>
	    <td class="heading">Description</td>
	    <td class="heading">Date</td>
	    <td class="heading">Hall</td>
	    <c:if test="${logged}">
	    <td class="headingSMALL">Order</td>
	    </c:if>
	   </tr>
	   <c:forEach var="concert" items="${concertsList}">
	    <tr>
	     <td>${concert.name}</td>
	     <td>${concert.players}</td>
	     <td>${concert.description}</td>
	     <td>${concert.date}</td>
	     <td>${concert.hallName}</td>
	     
	     <c:if test="${logged}">
	     <td> <a href="<%=request.getContextPath()%>/makeOrderForm?id=${concert.id}">Order ticket(s)</a> </td>
	     </c:if>
	    
	    </tr>
	   </c:forEach>
	  </table>
	
	 <br/><br/><br/>
	 
	 <a href="<%=request.getContextPath()%>/index">Back</a> | <a href="<%=request.getContextPath()%>/redirectCustomerPage">Your Customer Page</a>
	 
	 </center>
	</body>
</html>
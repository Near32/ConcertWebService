<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
    	<title>New Registration</title>
	</head>

	<body>

		<h2>Submitted Customer Information</h2>
   		
   		<table>
    	<tr>
        	<td>First Name</td>
        	<td>${firstName}</td>
        </tr>
        <tr>
        	<td>Last Name</td>
        	<td>${lastName}</td>
    	</tr>
    	
    	<tr>
        	<td>E-mail</td>
        	<td>${email}</td>
    	</tr>
    
    	<tr>
        	<td>Address</td>
        	<td>${address}</td>
    	</tr>
    	
    	<tr>
        	<td>Phone Number</td>
        	<td>${phoneNumber}</td>
    	</tr>
		</table>
		
		<p>
		Your registration has been successful, thank you.
		</p>
		
		<form:form method="GET" action="/ConcertWebService0.1/index">
			<table>
				<tr>
				<td>
				<input type="submit" value="Come back to the Homepage"/>
				</td>
				</tr>
			</table>
		</form:form>  
	</body>
	
	
</html>
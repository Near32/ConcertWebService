<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>New Customer</title>
</head>
<body>

<h2>Customer Information</h2>
<form:form method="POST" action="/ConcertWebService0.1/addCustomer">
   <table>
    <tr>
        <td><form:label path="firstName">First name</form:label></td>
        <td><form:input path="firstName" /></td>
    </tr>
    
    <tr>
        <td><form:label path="lastName">Last name</form:label></td>
        <td><form:input path="lastName" /></td>
    </tr>
    
    <tr>
        <td><form:label path="email">E-mail</form:label></td>
        <td><form:input path="email" /></td>
    </tr>
    
    <tr>
        <td><form:label path="address">Address</form:label></td>
        <td><form:input path="address" /></td>
    </tr>
    
    <tr>
        <td><form:label path="phoneNumber">Phone number</form:label></td>
        <td><form:input path="phoneNumber" /></td>
    </tr>
    
     <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:input path="password" /></td>
    </tr>
    
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>
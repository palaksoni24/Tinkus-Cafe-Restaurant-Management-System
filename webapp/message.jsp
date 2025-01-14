<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	String staus = (String) request.getAttribute("status");
	String message = (String) request.getAttribute("message");
	String redirectUrl = (String) request.getAttribute("redirectUrl");
	%>

	<h1><%=staus%></h1>
	<p><%=message%></p>
	<a href="<%=redirectUrl%>">Click Here To Redirect</a>
</body>
</html>
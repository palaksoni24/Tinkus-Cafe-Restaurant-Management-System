<%@page import="java.util.List"%>
<%@page import="com.amstech.tinkus.backend.dto.UserDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>home Page</title>

</head>
<body>
<%
	if (request.getAttribute("activeUserDTO") != null) {
		UserDTO activeUserDTO = (UserDTO) request.getAttribute("activeUserDTO");
		if (activeUserDTO != null) {
	%>
	<h1>
		Welcome
		<%=activeUserDTO.getFirstName()%></h1>

	<%
	}
	}
	%>

	<label for="mnumber">Mobile Number:</label>
	<br>

	<form action="user" method="get">
		<input type="hidden" name="task" value="findByMobile">
		 <input type="text" id="mnumber" class="form-input" placeholder="enter your mobile number" required="required" name="mobileNumber" /> 
			<input type="submit" value="Find By Number">
	</form>

	<br> OR
	<br>
	<form action="user" method="get">
		<input type="hidden" name="task" value="findAll"> <input
			type="submit" value="Find All">
	</form>

	<br>
	<br>

	<table>
		<tr>
			<th>#</th>
			<th>First_name</th>
			<th>lastName</th>
			<th>Email</th>
			<th>Mobile Number</th>
			<th>Address</th>
			<th>Pan Number</th>
			<th>Image</th>
		</tr>

		<%
		if (request.getAttribute("userDTO") != null) {
			UserDTO userDTO = (UserDTO) request.getAttribute("userDTO");
			if (userDTO != null) {
		%>

		<tr>
			<td>1</td>
			<td><%=userDTO.getFirstName()%></td>
			<td><%=userDTO.getLastName()%></td>
			<td><%=userDTO.getEmail()%></td>
			<td><%=userDTO.getMobileNumber()%></td>
			<td><%=userDTO.getAddress()%></td>
			<td><%=userDTO.getPanNumber()%></td>
			<td><img src"<%=userDTO.getImage() %>">
			
			<td>
                   <button type="button" class="button1">edit</button>
				<form action="user" method="get">
					<input type="hidden" name="task" value="deleteById">
					 <input type="hidden" name="id" value="<%=userDTO.getId()%>">
						
					<button type="submit">delete</button>
				</form>


			</td>
		</tr>

		<%
		}
		}
		%>

		<%
		if (request.getAttribute("userDTOList") != null) {
			List<UserDTO> userDTOList = (List) request.getAttribute("userDTOList");
			for (UserDTO userDTO1 : userDTOList) {
		%>
		<tr>
			<td>1</td>
			<td><%=userDTO1.getFirstName()%></td>
			<td><%=userDTO1.getLastName()%></td>
			<td><%=userDTO1.getEmail()%></td>
			<td><%=userDTO1.getMobileNumber()%></td>
			<td><%=userDTO1.getAddress()%></td>
			<td><%=userDTO1.getPanNumber()%></td>
			<td><%=userDTO1.getImage()%>

				<form action="user" method="get">
					<input type="hidden" name="task" value="findById"> <input
						type="hidden" name="id" value="<%=userDTO1.getId()%>">
					<button type="submit" class="button1">edit</button>
				</form>

				<form action="user" method="get">
					<input type="hidden" name="task" value="deleteById"> <input
						type="hidden" name="id" value="<%=userDTO1.getId()%>">
					<button type="submit">delete</button>
				</form>



			</td>
		</tr>
		<%
		}
		}
		%>

	</table>

	

	<%
	if (request.getAttribute("userDTOEdit") != null) {
		UserDTO userDTOEdit = (UserDTO) request.getAttribute("userDTOEdit");
	%>

	<h1>Edit User</h1>
	<form action="user" method="post">
		<input type="hidden" name="task" value="updateById"> <input
			type="hidden" name="id" value="<%=userDTOEdit.getId()%>">
		<table>
			<tr>
				<td>FirstName:</td>
				<td><input type="text" name="firstName"
					value="<%=userDTOEdit.getFirstName()%>" /></td>


			</tr>

			<tr>
				<td>LastName:</td>
				<td><input type="text" name="lastName"
					value="<%=userDTOEdit.getLastName()%>" /></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><input type="text" name="email"
					value="<%=userDTOEdit.getEmail()%>" /></td>
			</tr>

			<tr>
				<td>MobileNumber:</td>
				<td><input type="text" name="mobileNumber"
					value="<%=userDTOEdit.getMobileNumber()%>" /></td>
			</tr>

			
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address"
					value="<%=userDTOEdit.getAddress()%>" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Update" /></td>
				<td><input type="reset" name="reset" /></td>

			</tr>

		</table>
	</form>

	<%
	}
	%>
</body>
</html>
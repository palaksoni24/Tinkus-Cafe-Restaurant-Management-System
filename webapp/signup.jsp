<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Signup Page</title>

</head>
<body>

<fieldset>
	<h2>Signup Page</h2>
		<form action="user" method="POST">
		<input type="hidden" name= "task" value = "signup"/>
			<label for="fname">First Name:</label><br>
		    <input type="text" id="fname" class="form-input" placeholder="enter your first name" required="required"  name="firstName"/><br>
			<br> <label for="pass">Last Name:</label><br> 
			<input type="text" id="lname" class="form-input" placeholder="enter your last name" required="required" name="lastName"/><br><br>
			<label for="email">Email:</label><br>
		    <input type="text" id="email" class="form-input" placeholder="enter your email" required="required"name="email" /><br><br>
		    <label for="mnumber">Mobile Number:</label><br>
		    <input type="text" id="mnumber" class="form-input" placeholder="enter your mobile number" required="required" name="mobileNumber"/><br><br>
			<br> <label for="address">Address:</label><br>
			<input type="text" id="address" class="form-input"name="address" /><br>
			 	<br> <label for="pannumber">Pan Number:</label><br> 
			<input type="text" id="pannumber" class="form-input" name="panNumber" /><br><br>
			<label for="password">Password:</label><br> 
			<input type="password" id="mnumber" class="form-input" placeholder="enter your password" required="required" name="password" />
			<br>
			
		<input type="submit" value="Submit">
		 <input type="reset" value="Reset">
	
	</form>
</fieldset>

</body>
</html>
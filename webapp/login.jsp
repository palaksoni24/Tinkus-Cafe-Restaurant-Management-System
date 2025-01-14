<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login</title>

</head>
<body>
	
	<fieldset>
	<h2>Login Page</h2>
		<form action ="user" method="post">
		<input type ="hidden" name="task" value="Login"/>
		
			<label for="mnumber">Mobile Number:</label><br>
		    <input type="text" id="mnumber" class="form-input" placeholder="enter your mobile number" required="required" name="mobileNumber" /><br>
			<br> <label for="pass">Password:</label><br> 
			<input type="text" id="pass" class="form-input" placeholder="enter your password" required="required" name="password"/><br><br>
			
			 <input type="submit" class="button" name="login" value="Login"/>
		     <input type="reset" class="button"/>
	
	</form>
</fieldset>


</body>
</html>
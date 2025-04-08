<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Admin Login Page</title>
</head>
<body>
	${invaliduser}
	<a href="/">Quiz Application page</a>
	<br />
	<form action="/verifyadmin">
		<input type="text" placeholder="Enter Username" name="username"
			required><br /> <input type="password"
			placeholder="Enter Password" name="password" required><br />
			<input type="text" value="admin" name="usertype" hidden>
		<button id="submit" type="submit">Login</button>
	</form>
</body>
</html>
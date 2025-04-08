<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
</head>
<body>
	User Login Page ${invaliduser} ${alreadyregistered } ${userregisterd }
	<a href="index">Quiz Application page</a>
	<br />
	<form action="/verifyuser" method="post">
		<input type="text" placeholder="Enter Username" name="username"
			required><br /> <input type="password"
			placeholder="Enter Password" name="password" required><br />
		<button id="submit" type="submit">Login</button>
		<button>
			<a href="userregister">register</a>
		</button>
	</form>
</body>
</html>
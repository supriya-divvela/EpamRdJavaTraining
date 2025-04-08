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
	<br />
	<a href="/">Quiz Application page</a>
	<br />
	<form action="/verifyuser">
		<input type="text" placeholder="Enter Username" name="username"
			required><br /> <input type="password"
			placeholder="Enter Password" name="password" required><br />
		<input type="text" value="user" name="usertype" hidden>
		<button>
			<a href="userregister">register</a>
		</button>
		<button id="submit" type="submit">Login</button>

	</form>
</body>
</html>
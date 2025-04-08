<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Admin Home Page</title>
</head>
<body>
	Welcome to admin dashboard...
	<br /> ${login} ${adminregisterd}
	<a href="adminregister">Add a new admin</a>
	<a href="questionpage">Questions Page</a>
	<br />
	<a href="quizpage">Quiz Page</a>
	<br />
	<a href="logout">Logout</a>
</body>
</html>
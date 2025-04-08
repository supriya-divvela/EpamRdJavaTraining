<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Create Quiz Page</title>
</head>
<body>
	<a href="adminhome">Admin Home Page</a>
	<br />
	<a href="quizpage">Quiz Page</a>
	<br />
	<br /> Create Quiz Page
	<br />
	<br /> ${duplicatequiz} ${createquiz}
	<form action="/addquiz" method="post">
		<input type="text" placeholder="Enter Quiz Number" name="quizNo"
			required><br /> <input type="text" placeholder="Enter title"
			name="title" required><br /> <input type="text"
			placeholder="Enter question numbers seperated by comma..."
			name="listOfQuestions" required><br />
		<button id="submit" type="submit">create quiz</button>
	</form>
	<a href="logout">Logout</a>
</body>
</html>
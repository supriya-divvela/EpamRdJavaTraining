<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Update Quiz Page</title>
</head>
<body>
	<a href="adminhome">Admin Home Page</a>
	<a href="createquiz">create quiz</a>
	<br />
	<br /> Update Quiz Page
	<a href="logout">Logout</a>
	<form action="/updatequizwithdetails" method="post">
		<input type="text" placeholder="Enter Question Number" name="quizNo"
			value="${quiz.getQuizNo()}" required readonly><br /> <input
			type="text" placeholder="Enter title" name="title"
			value="${quiz.getTitle()}" required><br /> <input
			type="text" placeholder="Enter questions seperated by comma.."
			name="listOfQuestions" value="${questionnumbers}" required><br />
		<button id="submit" type="submit">update quiz</button>
	</form>
</body>
</html>
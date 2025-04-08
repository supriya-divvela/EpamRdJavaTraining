<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>User Page</title>
</head>
<body>
	User Logged In Succesfully....!
	<br /> All Available Quizes
	<br />
	<a href="logout">Logout</a>
	<table border="1">
		<caption>List OF Quizes</caption>
		<tr>
			<th>Quiz Number</th>
			<th>Title</th>
			<th>List Of Questions</th>
			<th>attempt quiz</th>

		</tr>
		<c:forEach var="quiz" items="${listOfQuizs}">
			<tr>
				<td>${ quiz.getQuizNo()}</td>
				<td>${ quiz.getTitle()}</td>
				<td>${ quiz.getListOfQuestions()}</td>
				<td>
					<button>
						<a href="attemptquiz?quizNo=${quiz.getQuizNo()}">attempt quiz</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	${emptyquizlibrary }
</body>
</html>
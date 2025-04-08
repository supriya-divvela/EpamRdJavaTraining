<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Quiz Page</title>
</head>
<body>
	<a href="adminhome">Admin Home Page</a>
	<br />
	<a href="quizpage">Quiz Page</a>
	<br />
	<a href="createquiz">create quiz</a>
	<br />
	<a href="logout">Logout</a>
	<br /> Welcome to quiz page
	<table border="1">
		<caption>List OF Quizes In Quiz Library</caption>
		<tr>
			<th>Quiz Number</th>
			<th>Title</th>
			<th>List Of Questions</th>
			<th>delete quiz</th>
			<th>update quiz</th>
		</tr>
		<c:forEach var="quiz" items="${listOfQuizs}">
			<tr>
				<td>${ quiz.getQuizNo()}</td>
				<td>${ quiz.getTitle()}</td>
				<td>${ quiz.getListOfQuestions()}</td>
				<td>
					<button>
						<a href="deletequiz?quizNo=${quiz.getQuizNo()}">delete quiz</a>
					</button>
				</td>
				<td>
					<button>
						<a href="updatequiz?quizNo=${quiz.getQuizNo()}">update quiz</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	${emptyquizlibrary } ${deletequizmessage }
</body>
</html>
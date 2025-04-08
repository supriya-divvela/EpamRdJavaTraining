<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Attempt quiz page</title>
</head>
<body>
	attempt Page
	<a href="user">User Home page</a>
	<br />Quiz attempted succesfully..
	</br>
	<a href="logout">Logout</a>.
	<form action="/marks">
		<input type="text" name="quizNo" value="${quizNo}" hidden>
		<table border="1">
			<caption>Questions in the quiz</caption>
			<tr>
				<th>Question</th>
				<th>Answer</th>
			</tr>
			<c:forEach var="question" items="${questions}">
				<tr>
					<td>${ question}</td>
					<td><input type="text" name="answers" required></td>
				</tr>
			</c:forEach>
		</table>
		<input id="submit" type="submit" value="submit quiz">
	</form>
</body>
</html>
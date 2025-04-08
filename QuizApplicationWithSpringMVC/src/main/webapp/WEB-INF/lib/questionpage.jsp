<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.epam.model.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Question Page</title>
</head>
<body>
	<a href="adminhome">Admin Home Page</a>
	<br />
	<a href="quizpage">Quiz Page</a>
	<br />
	<a href="logout">Logout</a>
	<br /> Welcome to question page
	<br />
	<a href="/createquestion">create a question</a> All Available questions

	<table border="1">
		<caption>List OF Available Questions In Question Library</caption>
		<tr>
			<th>Question Number</th>
			<th>Title</th>
			<th>Options</th>
			<th>Difficulty</th>
			<th>Tagging topic</th>
			<th>answers</th>
			<th>marks</th>
			<th>delete question</th>
			<th>update question</th>
		</tr>
		<c:forEach var="question" items="${listOfQuestions}">
			<tr>
				<td>${ question.getQNo()}</td>
				<td>${ question.getTitle()}</td>
				<td>${ question.getOptions()}</td>
				<td>${ question.getDifficulty()}</td>
				<td>${ question.getTaggingTopic()}</td>
				<td>${ question.getAnswers()}</td>
				<td>${ question.getMarks()}</td>
				<td>
					<button>
						<a href="deletequestion?qNo=${question.getQNo()}" />delete
						question</a>
					</button>
				</td>
				<td>
					<button>
						<a href="updatequestion?qNo=${question.getQNo()}" />update
						question</a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	${emptyquestionlibrary } ${updatequestion} ${deletequestionmessage }
</body>
</html>
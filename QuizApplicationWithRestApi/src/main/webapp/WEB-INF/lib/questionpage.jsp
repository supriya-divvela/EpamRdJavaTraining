<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Questions Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/adminhome">Admin Home</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link"
					href="/adminregister">Admin Register</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="/questionpage">Question Library</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="/quizpage">Quiz Library</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="/createquestion">Create Question</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="/createquiz">Create Quiz</a></li>
			</ul>
		</div>
		<div class="navbar-nav ml-auto">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="/adminlogin">Logout</a></li>
			</ul>

		</div>
	</nav>
	<div class="container mt-4">
		<h3 class="text-center my-4">Welcome to Questions Page</h3>
		<h3 style="color: red;">${cannotdelete }
			${updatequestion} ${deletequestionmessage }</h3>
		<table class="table table-striped">
			<caption>List Of All Available Questions</caption>
			<thead>
				<tr>
					<th scope="col">Question Number</th>
					<th scope="col">Title</th>
					<th scope="col">Options</th>
					<th scope="col">Difficulty</th>
					<th scope="col">Tagging Topic</th>
					<th scope="col">Answers</th>
					<th scope="col">Marks</th>
					<th scope="col">Delete Question</th>
					<th scope="col">Update Question</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="question" items="${listOfQuestions}">
					<tr>
						<td>${ question.getId()}</td>
						<td>${ question.getTitle()}</td>
						<td>${ question.getOptions()}</td>
						<td>${ question.getDifficulty()}</td>
						<td>${ question.getTaggingTopic()}</td>
						<td>${ question.getAnswers()}</td>
						<td>${ question.getMarks()}</td>

						<td><div class="form-group">
								<a href="deletequestion?qNo=${question.getId()}"
									class="btn btn-danger ml-3">Delete</a>
							</div></td>
						<td><div class="form-group">
								<a href="updatequestion?qNo=${question.getId()}"
									class="btn btn-success ml-3">Update</a>
							</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<footer class="bg-dark text-white text-center py-2">
		<p>&copy; 2023 Quiz Application. All rights reserved.</p>
	</footer>
</body>
</html>
